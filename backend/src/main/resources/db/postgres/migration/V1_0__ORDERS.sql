create table if not exists orders
(
	id serial not null,
	product VARCHAR not null,
	sum int not null,
	comment varchar
);

create unique index orders_id_uindex
	on orders (id);

alter table orders
	add constraint orders_pk
		primary key (id);

