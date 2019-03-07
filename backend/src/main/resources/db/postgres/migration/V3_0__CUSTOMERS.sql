create table customers
(
  id serial not null,
  name varchar not null
);

create unique index customers_id_uindex
  on customers (id);

alter table customers
  add constraint customers_pk
    primary key (id);

