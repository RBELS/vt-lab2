-- DROP TABLE "BOOK"
-- DROP TABLE "USER"

create table BOOK
(
    BOOK_ID   serial primary key,
    NAME      varchar(256),
    AUTHOR    varchar(256),
    PRICE     numeric(12, 2),
    description varchar(4096)
);

create table USER
(
    USER_ID  serial primary key,
    USERNAME varchar(64) unique,
    HASH     varchar(50)
);
