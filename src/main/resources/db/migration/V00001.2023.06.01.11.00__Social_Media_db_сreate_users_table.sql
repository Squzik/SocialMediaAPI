create table users
(
    name                      varchar(255) unique,
    mail                      varchar(255) unique,
    password                  text,
    id                        uuid    default gen_random_uuid() not null
        primary key
        constraint users_nid_key
            unique
);

alter table users
    owner to sma;