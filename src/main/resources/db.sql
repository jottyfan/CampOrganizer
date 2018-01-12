create schema camp;
grant usage on schema camp to camp;

set search_path = camp;

create type enum_role as enum ('subscriber', 'registrator', 'businessman', 'admin');
create type enum_document as enum ('camppass', 'location', 'camp');
create type enum_filetype as enum ('pdf', 'png', 'jpg');

create table t_document (pk serial primary key, 
                         doctype enum_document, 
                         name text, 
                         document text, 
                         filetype enum_filetype, 
                         unique(name));

grant select,insert,update,delete on t_document to camp;
grant usage on t_document_pk_seq to camp;

create table t_profile (pk serial primary key, 
                        forename text, 
                        surname text, 
                        username text not null, 
                        password text not null,
                        duedate timestamp default now() + interval '1 year',
                        uuid text not null,
                        unique(username),
                        unique(uuid));

grant select,insert,update,delete on t_profile to camp;                        
grant usage on t_profile_pk_seq to camp;
                        
create table t_profilerole (fk_profile integer not null,
                            role enum_role not null,
                            unique(fk_profile, role),
                            foreign key (fk_profile) references t_profile(pk));

grant select,insert,update,delete on t_profilerole to camp;                            

create table t_location (pk serial primary key,
                         name text not null,
                         url text,
                         fk_document integer,
                         foreign key (fk_document) references t_document(pk));

grant select,insert,update,delete on t_location to camp;                         
grant usage on t_location_pk_seq to camp;
                         
create table t_camp (pk serial primary key,
                     name text not null,
                     arrive timestamp not null,
                     depart timestamp not null,
                     fk_location integer not null,
                     min_age integer not null,
                     max_age integer not null,
                     price text,
                     countries text,
                     fk_document integer,
                     lock_sales boolean not null default false,
                     foreign key (fk_location) references t_location(pk),
                     foreign key (fk_document) references t_document(pk));

grant select,insert,update,delete on t_camp to camp;
grant usage on t_camp_pk_seq to camp;

create table t_sales (pk serial primary key,
                      trader text,
                      fk_camp integer not null,
                      provider text,
                      cash numeric(11,2) not null,
                      incredients text,
                      buydate timestamp,
                      recipenumber text,
                      recipeshot bytea,
                      recipenote text,
                      foreign key (fk_camp) references t_camp(pk));

grant select,insert,update,delete on t_sales to camp;
grant usage on t_sales_pk_seq to camp;

create table t_salescontenttype (name text not null primary key);

grant select,insert,update,delete on t_salescontenttype to camp;

create table t_salescontent (fk_sales integer not null, 
                             fk_salescontenttype text not null,
                             unique (fk_sales, fk_salescontenttype));

grant select,insert,update,delete on t_salescontent to camp;

create view v_profile as 
select pk,
       forename,
       surname,
       username,
       password,
       uuid,
       array_agg(role) as roles
from t_profile 
left join t_profilerole on fk_profile = pk
where duedate > now()
group by pk, forename, surname, username, password;

grant select on v_profile to camp;

create view v_sales as 
select s.pk,
       s.trader,
       c.pk as fk_camp,
       c.name,
       l.pk as fk_location,
       l.name as location,
       s.incredients,
       extract(isoyear from c.arrive) as year,
       s.pk as fk_sales,
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
group by 1,2,3,4,5,6,7,8,9,10,11,12,13,14;

grant select on v_sales to camp;

create view v_budget as
select sum(cash) as budget, fk_camp, name, location, year from v_sales
group by 2,3,4,5;

grant select on v_budget to camp;

create view v_role as select unnest(enum_range(NULL::enum_role));

grant select on v_role to camp;

create table t_rss (pk serial,
                    recipient text,
                    msg text, 
                    regdate timestamp default now(),
                    primary key (pk));

grant select,insert,update,delete on t_rss to camp;
grant usage on sequence t_rss_pk_seq to camp;

create type enum_camprole as enum ('boy', 'girl', 'helperboy', 'helpergirl', 'kitchen');

create view v_camprole as select unnest(enum_range(NULL::enum_camprole)) as name;

grant select on v_camprole to camp;

create table t_person (pk serial primary key, 
                       forename text, 
                       surname text, 
                       street text, 
                       zip text, 
                       city text, 
                       phone text, 
                       birthdate date, 
                       camprole enum_camprole, 
                       email text, 
                       fk_camp integer, 
                       fk_profile integer,
                       accept boolean default false not null,
                       created timestamp default now(),
                       unique (forename, surname, birthdate, fk_camp),
                       foreign key (fk_camp) references t_camp(pk),
                       foreign key (fk_profile) references t_profile(pk));
                       
grant select,insert,update,delete on t_person to camp;

create view v_registration as
select t_person.pk, forename, surname, street, zip, city, phone, birthdate, camprole, email, name || extract(isoyear from arrive) as campname
from t_person
left join t_camp on t_camp.pk = fk_camp;

grant select on v_registration to camp;

grant usage on sequence t_person_pk_seq to camp;

/* 20170826 */
drop view if exists v_camp;

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
       c.countries,
       c.fk_document
from t_camp c
left join t_location l on c.fk_location = l.pk;
         
grant select on v_camp to camp;

/* 20170928 */

create table t_persondocument(pk serial primary key, 
                              fk_person integer not null, 
                              name text, 
                              document text, 
                              filetype enum_filetype, 
                              unique(fk_person, name),
                              foreign key (fk_person) references t_person(pk));

grant select,insert,update,delete on t_persondocument to camp;
grant usage on t_persondocument_pk_seq to camp;
