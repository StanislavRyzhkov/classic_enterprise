create table if not exists users
(
    id bigserial not null
        constraint users_pkey
            primary key,
    status varchar(255),
    email varchar(255) not null
        constraint uk_6dotkott2kjsp8vw4d0m25fb7
            unique,
    password varchar(255) not null,
    username varchar(255) not null
        constraint uk_r43af9ap4edm43mmtq01oddj6
            unique
);
alter table users owner to clyde;



create table if not exists roles
(
    id bigserial not null
        constraint roles_pkey
            primary key,
    name varchar(255)
);
alter table roles owner to clyde;



create table if not exists user_roles
(
    user_id bigint not null
        constraint fkhfh9dx7w3ubf1co1vdev94g3f
            references users,
    role_id bigint not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles
);
alter table user_roles owner to clyde;



create table if not exists accounts
(
    id bigserial not null
        constraint accounts_pkey
            primary key,
    first_name varchar(255),
    phone_number varchar(255),
    second_name varchar(255),
    user_id bigint
        constraint fknjuop33mo69pd79ctplkck40n
            references users
);
alter table accounts owner to clyde;



create table if not exists categories(
    id bigserial not null
        constraint categories_pkey
            primary key,
    name varchar(255)
);
alter table categories owner to clyde;


create table if not exists products(
    id bigserial not null
        constraint products_pkey
            primary key,
    name varchar(255),
    vendor_code varchar(255) not null,
    price int not null,
    picture varchar(255),
    created timestamp,
    category_id bigint
        constraint products_fk_category_id
            references categories
);
alter table products owner to clyde;




