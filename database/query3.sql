select text from messages where date_message='2016-05-09' and id_user in (select id_user from users where name='victorkozlov') ;