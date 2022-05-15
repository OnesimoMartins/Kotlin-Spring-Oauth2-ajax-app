CREATE TABLE user_books(
id_user INTEGER NOT NULL,
id_book INTEGER NOT NULL,

FOREIGN KEY(id_user) REFERENCES user(id),
FOREIGN KEY(id_user) REFERENCES book(id)
)