create sequence likes_id_seq;

create table likes
(
    id            bigint,
    game_title_id bigint not null,
    created_by    bigint not null,
    primary key (id),
    constraint fk_likes_game_titles
        foreign key (game_title_id) references game_title (id),
    constraint fk_likes_users
        foreign key (created_by) references users (id)
);

alter sequence likes_id_seq OWNED BY likes.id;