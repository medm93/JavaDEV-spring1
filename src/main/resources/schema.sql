create table if not exists Customer (
id int not null auto_increment,
nick varchar(25) not null,
email varchar(30) not null,
primary key(id)
);