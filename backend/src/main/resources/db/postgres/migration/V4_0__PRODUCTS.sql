create table products
(
  id serial not null,
  name varchar not null
);

create unique index products_id_uindex
  on products (id);

alter table products
  add constraint products_pk
    primary key (id);

