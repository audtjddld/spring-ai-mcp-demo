DROP TABLE IF EXISTS todo;

CREATE TABLE todo (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          description VARCHAR(255) NOT NULL,
                          date VARCHAR(255) NOT NULL,
                          reg_date_time TIMESTAMP,
                          last_update_time TIMESTAMP
);


-- INSERT INTO homework (id, title, description, date, reg_date_time, last_update_time)
-- VALUES (1, 'Homework 1', 'Description for Homework 1', '2023-12-01', NOW(), NOW());
--
-- ,
--        (2, 'Homework 2', 'Description for Homework 2', '2023-12-02', NOW(), NOW()),
--        (3, 'Homework 3', 'Description for Homework 3', '2023-12-03', NOW(), NOW()),
--        (4, 'Homework 4', 'Description for Homework 4', '2023-12-04', NOW(), NOW()),
--        (5, 'Homework 5', 'Description for Homework 5', '2023-12-05', NOW(), NOW()),
--        (6, 'Homework 6', 'Description for Homework 6', '2023-12-06', NOW(), NOW()),
--        (7, 'Homework 7', 'Description for Homework 7', '2023-12-07', NOW(), NOW()),
--        (8, 'Homework 8', 'Description for Homework 8', '2023-12-08', NOW(), NOW()),
--        (9, 'Homework 9', 'Description for Homework 9', '2023-12-09', NOW(), NOW()),
--        (10, 'Homework 10', 'Description for Homework 10', '2023-12-10', NOW(), NOW());
