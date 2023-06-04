create table files
(
    id         uuid   default gen_random_uuid()     not null
        primary key,
    users_id   uuid
        references users,
    post_id   uuid
        references post,
    bucket     varchar(15) not null,
    status     varchar(30) not null
);

alter table files
    owner to sma;

create table friend_requests
(
    id         uuid   default gen_random_uuid()     not null
        primary key,
    sender_id   uuid,
    receiver_id   uuid,
    accepted     bool

);

alter table friend_requests
    owner to sma;

create table friends
(
    id         uuid   default gen_random_uuid()     not null
        primary key,
    user_id   uuid,
    friend_id   uuid

);

alter table friends
    owner to sma;

