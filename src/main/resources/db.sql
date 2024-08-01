create database if not exists pos_system;

use pos_system;

create  table customer (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          address VARCHAR(200) NOT NULL,
                          salary DOUBLE NOT NULL
)