alter table comments
    drop constraint fk_comments_game_titles,
    drop constraint fk_comments_users,
    add  constraint fk_comments_game_titles foreign key (game_title_id) references game_title(id) on delete cascade,
    add  constraint fk_comments_users foreign key (created_by) references users(id) on delete cascade;

alter table likes
    drop constraint fk_likes_game_titles,
    drop constraint fk_likes_users,
    add  constraint fk_likes_game_titles foreign key (game_title_id) references game_title(id) on delete cascade,
    add  constraint fk_likes_users foreign key (created_by) references users(id) on delete cascade;

alter table game_title
    drop constraint game_title_users_id_fk,
    add  constraint fk_game_titles_users foreign key (created_by) references users(id) on delete cascade;
