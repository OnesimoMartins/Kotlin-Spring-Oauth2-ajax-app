create table book(
 id INTEGER not null auto_increment,
name varchar(50) not null,
rating tinyint not null default 0,
PRIMARY KEY(id)
);