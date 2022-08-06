insert into users
values ((select max(id) + 1 from users), 'goodgames@admin.com', 'goodgames', 'admin',
        '$2a$10$5uOMZcXYMKbE34FOzaCon.DtU2Sqv/jXhbPEJSWTkNjjkK.q/0T0y', 2)