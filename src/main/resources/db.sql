CREATE DATABASE IF NOT EXISTS pos_system;

USE pos_system;

CREATE TABLE customer (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          address VARCHAR(200) NOT NULL,
                          salary DOUBLE NOT NULL
);

CREATE TABLE item (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      description VARCHAR(100) NOT NULL,
                      qty INT NOT NULL,
                      price DOUBLE NOT NULL
);

CREATE TABLE `order` (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        date DATE NOT NULL,
                        total DOUBLE NOT NULL,
                        discount DOUBLE NOT NULL,
                        customer_id INT NOT NULL,
                        FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE order_detail (
                        order_id INT NOT NULL ,
                        item_id INT NOT NULL,
                        qty INT NOT NULL,
                        price DOUBLE NOT NULL,
                        FOREIGN KEY (order_id) REFERENCES `order`(id),
                        FOREIGN KEY (item_id) REFERENCES `item`(id)
);