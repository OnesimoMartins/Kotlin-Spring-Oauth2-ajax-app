ALTER TABLE user drop COLUMN password;
 ALTER TABLE user add COLUMN password VARCHAR(80) NOT NULL ;