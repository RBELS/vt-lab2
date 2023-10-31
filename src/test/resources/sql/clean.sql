create table if not exists `BOOK`
(
    BOOK_ID   bigint auto_increment primary key,
    NAME      varchar(256),
    AUTHOR    varchar(256),
    PRICE     numeric(12, 2),
    description varchar(4096)
);

create table if not exists `USER`
(
    USER_ID  bigint auto_increment primary key,
    USERNAME varchar(64) unique,
    HASH     varchar(50)
);

DELETE FROM `BOOK`;
DELETE FROM `USER`;


-- INSERT BOOKS
INSERT INTO `BOOK` (NAME, AUTHOR, DESCRIPTION, PRICE)
VALUES ('Book 1', 'Author 1', 'Description 1', 10.00),
       ('Book 2', 'Author 2', 'Description 2', 20.00),
       ('Book 3', 'Author 3', 'Description 3', 30.00),
       ('Book 4', 'Author 4', 'Description 4', 40.00),
       ('Book 5', 'Author 5', 'Description 5', 50.00);

-- INSERT USERS
INSERT INTO `USER` (USER_ID, USERNAME, HASH)
VALUES (1, 'user1', ''),
       (2, 'user2', ''),
       (3, 'user3', ''),
       (4, 'user4', ''),
       (5, 'user5', '');
