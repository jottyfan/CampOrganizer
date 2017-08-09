create type enum_role as enum ('subscriber', 'registrator', 'businessman', 'admin');

create table t_profile (pk serial primary key, 
                        forename text, 
                        surname text, 
                        username text not null, 
                        password text not null,
                        duedate timestamp default now() + interval '1 year');

create table t_profilerole (fk_profile integer not null,
                            role enum_role not null,
                            unique(fk_profile, role),
                            foreign key (fk_profile) references t_profile(pk));

create table t_location (pk serial primary key,
                         name text not null,
                         url text);
                            
create table t_camp (pk serial primary key,
                     name text not null,
                     arrive timestamp not null,
                     depart timestamp not null,
                     fk_location integer not null,
                     min_age integer not null,
                     max_age integer not null,
                     price text,
                     countries text,
                     foreign key (fk_location) references t_location(pk));
                            
create table t_sales (pk serial primary key,
                      trader text,
                      fk_camp integer not null,
                      provider text,
                      cash money not null,
                      buydate timestamp,
                      recipenumber text,
                      recipeshot bytea,
                      recipenote text,
                      foreign key (fk_camp) references t_camp(pk));

create table t_salescontenttype (name text not null primary key);

create table t_salescontent (fk_sales integer not null, 
                             fk_salescontenttype text not null,
                             unique (fk_sales, fk_salescontenttype));

create view v_camp as
select c.pk,
       c.depart < now() AS is_over,
       c.name,
       c.arrive,
       c.depart,
       extract(isoyear from c.arrive) as year,
       l.name as location_name,
       c.min_age,
       c.max_age,
       l.url,
       c.price,
       c.countries 
from t_camp c
left join t_location l on c.fk_location = l.pk;
                             
create view v_sales as 
select s.pk,
       s.trader,
       c.name,
       l.name as location,
       extract(isoyear from c.arrive) as year,
       s.provider,
       s.cash,
       s.buydate,
       s.recipenumber,
       s.recipeshot,
       s.recipenote,
       array_agg(t.fk_salescontenttype) as content
from t_sales s
left join t_camp c on c.pk = s.fk_camp
left join t_location l on l.pk = c.fk_location
left join t_salescontent t on t.fk_sales = s.pk
group by 1,2,3,4,5,6,7,8,9,10,11;
                             
create view v_profile as 
select pk,
       forename,
       surname,
       username,
       password,
       array_agg(role) as roles
from t_profile 
left join t_profilerole on fk_profile = pk
where duedate > now()
group by pk, forename, surname, username, password;

create view v_budget as
select sum(cash) as budget, location, year from v_sales
group by 2,3;

create view v_role as select unnest(enum_range(NULL::enum_role));

