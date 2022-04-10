CREATE SEQUENCE users_id_seq;

create table if not exists users
(
    id         bigint      not null,
    email      varchar(45) not null,
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    password   varchar(64) not null,
    primary key (id)
);

ALTER SEQUENCE users_id_seq OWNED BY users.id;