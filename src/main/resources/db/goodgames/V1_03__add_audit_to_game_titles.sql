alter table game_title
    add created_by bigint;

alter table game_title
    add constraint game_title_users_id_fk
        foreign key (created_by) references users;