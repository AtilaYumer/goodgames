create sequence comments_id_seq;

create table comments
(
    id            bigint,
    comment       varchar(255) not null,
    game_title_id bigint       not null,
    created_by    bigint       not null,
    created_date  timestamp    not null,
    primary key (id),
    constraint fk_comments_game_titles
        foreign key (game_title_id) references game_title (id),
    constraint fk_comments_users
        foreign key (created_by) references users (id)
);

alter sequence comments_id_seq OWNED BY comments.id;