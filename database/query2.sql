select text from messages where id_user in (select id_user from users where name='victorkozlov');