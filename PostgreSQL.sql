-- create table systemUser (
	_id uuid not null primary key,
	account varchar(128) not null unique,
	password varchar(128),
	name varchar(128)
)

create table goods (
	_id uuid not null primary key,
	name varchar(128) not null,
	cr_user uuid,
	cr_datetime timestamp,
	up_user uuid,
	up_datetime timestamp,
	FOREIGN KEY(cr_user) REFERENCES systemUser(_id),
	FOREIGN KEY(up_user) REFERENCES systemUser(_id)
);

insert into systemUser (_id,account,password)
values('4d084bcf-2282-4c5e-9b20-4b18a684758d','user1','123');

select * from systemUser