CREATE SEQUENCE game_title_id_seq;

create table if not exists game_title
(
    id          bigint        not null,
    title       varchar(20)   not null,
    description varchar(60)   not null,
    image_url    varchar(100) not null,
    primary key (id)
);

ALTER SEQUENCE game_title_id_seq OWNED BY game_title.id;