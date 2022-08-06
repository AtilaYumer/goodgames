create table user_roles
(
    id   bigint,
    role varchar(255) not null,
    primary key (id),
    constraint uq_role
        unique (role)
);

insert into user_roles (id, role)
values (1, 'USER'),
       (2, 'ADMIN');

alter table users
    add column role_id bigint not null default 1;
