select name from users where id_user in (select id_user from messages group by id_user having current_date() and count(text)>3 ) ;