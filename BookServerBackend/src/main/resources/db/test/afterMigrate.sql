set foreign_key_checks = 0;

delete from user;
delete from book;
delete from authoritie;
delete from user_authorities;
delete from user_books;

set foreign_key_checks = 1;
ALTER TABLE user  AUTO_INCREMENT=1;
ALTER TABLE book  AUTO_INCREMENT=1;
ALTER TABLE authoritie  AUTO_INCREMENT=1;

insert into book(name,rating) value('as cinquenta sombras de grey',5);
insert into book(name,rating) values('Pai rico pai pobre',7),('Como ser uma pessoa melhor',3),
('O amor em mil dias',5),('Game of thrones',9.5),('Naruto uzumaki  2',4);

insert into user(name,email,password) values
('Ana da silva','AnaSilva@gamail.com', '$2a$10$Gu0Rbv.bc5tw.PjKeu5lfemapVDIekPIn5n5B/ekOjHiC0A9hSaq6')
,('Rui freitas','Rui@Hotmail.com','$2a$10$MPjw5WDC5qnk4sviVLkh9eJOi7vWP2MYgWIp9MnyZVx.m63zS.jRu')
,('preciosa lima','limas@gamail.com','$2a$10$Gu0Rbv.bc5tw.PjKeu5lfemapVDIekPIn5n5B/ekOjHiC0A9hSaq6')
,('graciana fretas','freitas@gamail.com','$2a$10$Gu0Rbv.bc5tw.PjKeu5lfemapVDIekPIn5n5B/ekOjHiC0A9hSaq6');

INSERT INTO authoritie(name) values ('USER'),('ADMIN');
INSERT INTO user_authorities (id_user,id_authoritie) values (1,1),(2,2),(2,1),(4,1);
INSERT INTO user_books(id_user,id_book) values(1,1),(1,2),(1,3)