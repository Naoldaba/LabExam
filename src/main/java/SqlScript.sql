CREATE DATABASE online_bookstore;

CREATE TABLE Books (
	id int auto_increament primary key,
	title varchar(255) not null,
	author varchar(15) not null,
	price double
);