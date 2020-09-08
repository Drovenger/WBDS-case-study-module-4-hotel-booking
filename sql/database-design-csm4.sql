DROP DATABASE IF EXISTS hotel_booking;
CREATE DATABASE hotel_booking;
USE hotel_booking;

create table if not exists management
(
    id         int primary key auto_increment,
    manager_id int not null
) charset = utf8mb4;

create table if not exists hotel
(
    id          int auto_increment primary key,
    manager_id  int                                not null,
    name        varchar(255)                       not null,
    description text                               null,
    status      int                                not null,
    create_date datetime default CURRENT_TIMESTAMP null,
    update_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,

    constraint hotel_manager_fk foreign key (manager_id) references management (id)
) charset = utf8mb4;

create table if not exists hotel_room
(
    id          int auto_increment primary key,
    hotel_id    int                                not null,
    name        varchar(255)                       not null,
    description text                               null,
    status      int                                not null,
    create_date datetime default CURRENT_TIMESTAMP null,
    update_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,

    constraint room_hotel_fk foreign key (hotel_id) references hotel (id)
) charset = utf8mb4;

CREATE TABLE IF NOT EXISTS room_status
(
    id          int auto_increment primary key,
    room_id     int                                not null,
    date        datetime default CURRENT_TIMESTAMP not null,
    room_status int                                not null,
    create_date datetime default CURRENT_TIMESTAMP null,
    update_date datetime default CURRENT_TIMESTAMP null,

    CONSTRAINT status_room_fk FOREIGN KEY (room_id) REFERENCES hotel_room (id)
) charset = utf8mb4;

create table if not exists attachment
(
    id          int auto_increment
        primary key,
    room_id     int                                not null,
    image_link  text                               not null,
    status      int                                not null,
    create_date datetime default CURRENT_TIMESTAMP null,
    update_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,

    constraint attachment_room_fk foreign key (room_id) references hotel_room (id)
) charset = utf8mb4;

create table if not exists role
(
    id          int auto_increment primary key,
    role_name   varchar(255)                       not null,
    create_date datetime default CURRENT_TIMESTAMP null,
    update_date datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
) charset = utf8mb4;

create table if not exists user
(
    id            int auto_increment primary key,
    role_id       int                                not null,
    first_name    varchar(255)                       not null,
    last_name     varchar(255)                       not null,
    gender        bit                                not null,
    date_of_birth date                               not null,
    phone         varchar(30)                        not null,
    address       varchar(255)                       not null,
    email         varchar(255)                       not null,
    username      varchar(255)                       not null,
    password      varchar(50)                        not null,
    status        int                                not null,
    created_date  datetime default CURRENT_TIMESTAMP null,
    update_date   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,

    constraint user_role_fk foreign key (role_id) references role (id)
) charset = utf8mb4;

alter table user
    alter column role_id set default 1;

alter table user
    alter column status set default 1;

create table if not exists bill
(
    id              int auto_increment primary key,
    user_id         int                                not null,
    message         text                               null,
    discount        double                             null,
    payment         varchar(255)                       not null,
    date_of_payment datetime                           not null,
    status          int      default 0                 not null,
    create_date     datetime default CURRENT_TIMESTAMP null,
    update_date     datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,

    constraint bill_user_fk foreign key (user_id) references user (id)
) charset = utf8mb4;

create table if not exists bill_details
(
    id             int auto_increment primary key,
    bill_id        int                                not null,
    room_status_id int                                not null,
    price          double                             not null,
    create_date    datetime default CURRENT_TIMESTAMP null,
    update_date    datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,

    constraint bill_details_cart_shopping_fk foreign key (bill_id) references bill (id),
    constraint bill_details_product_detail_fk foreign key (room_status_id) references room_status (id)
) charset = utf8mb4;