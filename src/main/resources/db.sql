drop table if exists t_profile cascade;
drop type if exists enum_role cascade;

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

create view v_role as select unnest(enum_range(NULL::enum_role));

