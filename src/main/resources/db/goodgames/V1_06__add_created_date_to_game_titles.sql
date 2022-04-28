alter table game_title add column created_date timestamp not null default current_timestamp;
SELECT setval('comments_id_seq', max(id)) FROM comments;
SELECT setval('game_title_id_seq', max(id)) FROM game_title;
SELECT setval('likes_id_seq', max(id)) FROM likes;
SELECT setval('users_id_seq', max(id)) FROM users;