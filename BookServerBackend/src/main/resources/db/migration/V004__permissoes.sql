create table authoritie(
id INTEGER not null auto_increment,
name varchar(100) not null UNIQUE
, PRIMARY KEY(id)
);

create table user_authorities(
id_user INTEGER NOT NULL,
id_authoritie INTEGER NOT NULL,

FOREIGN KEY (id_user) REFERENCES user(id),
FOREIGN KEY (id_authoritie) REFERENCES authoritie(id)
)