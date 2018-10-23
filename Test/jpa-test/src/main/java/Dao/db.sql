drop DATABASE if EXISTS


use test;
CREATE TABLE country
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(15),
    description VARCHAR(255)
)engine=InnoDB charset=utf8;


CREATE TABLE user
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    countryid INT NOT NULL,
    name VARCHAR(15),
    description VARCHAR(255)
)engine=InnoDB charset=utf8;
