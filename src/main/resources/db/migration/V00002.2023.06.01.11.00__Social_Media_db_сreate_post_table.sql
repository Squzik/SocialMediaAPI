create table post
(
    id      uuid default gen_random_uuid() not null primary key,
    title    varchar(255) not null,
    content     varchar(255) not null,
    created_at timestamp not null ,
        users_id   uuid references users
);

alter table post
    owner to sma;

create table chat
(
    id         uuid   default gen_random_uuid()     not null
        primary key,
    user_id   uuid,
    message_id   uuid

);

alter table chat
    owner to sma;



create table message
(
    id         uuid   default gen_random_uuid()     not null
        primary key,
    user_id   uuid,
    chat_id   uuid,
    text varchar(255) not null

);

alter table message
    owner to sma;


create table chat_users
(
    users_id   uuid,
    chats_id   uuid

);

alter table chat_users
    owner to sma;
