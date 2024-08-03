create database if not exists pos_system;

use pos_system;

create  table customer (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          address VARCHAR(200) NOT NULL,
                          salary DOUBLE NOT NULL
);

create  table item (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           description VARCHAR(100) NOT NULL,
                           qty INT(200) NOT NULL,
                           price DOUBLE NOT NULL
);