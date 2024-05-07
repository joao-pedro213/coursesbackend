create table course (
    id serial primary key,
    name varchar(255) not null,
    category varchar(255) not null,
    status varchar(25) not null,
    created_at timestamp not null,
    updated_at timestamp not null
);