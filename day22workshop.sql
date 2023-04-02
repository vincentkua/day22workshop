
create database rsvp;
use rsvp;

drop table rsvp;

create table rsvp(
id int not null auto_increment,
full_name varchar(100),
email varchar(100) unique,
phone varchar(20),
confirmation_date date,
comments text,
constraint rsvp_pk primary key(id)
);

insert into rsvp (full_name,email,phone,confirmation_date,comments) values ('Peter Lim' , 'peter@gmail.com' , '99998888' , '2023-03-26' , 'Lorem Ipsum Set amet...');
insert into rsvp (full_name,email,phone,confirmation_date,comments) values ('Mary Sheep' , 'mary@gmail.com' , '11223344' , '2022-03-26' , 'Lorem Ipsum Set amet...');
insert into rsvp (full_name,email,phone,confirmation_date,comments) values ('Vincent' , 'vincent@gmail.com' , '11223344' , '2022-03-26' , 'Lorem Ipsum Set amet...');
insert into rsvp (full_name,email,phone,confirmation_date,comments) values ('Alex' , 'alex@gmail.com' , '11223344' , '2022-02-26' , 'Lorem Ipsum Set amet...');

select * from rsvp;

select * from rsvp where full_name like  '%peter%';

update rsvp
set email = 'peter@gmail.com' , phone = '2222222' , confirmation_date = '2019-12-12' , comments = 'nothing...'
where id = 1;

update rsvp
set email = 'peter2@gmail.com' , phone = '2222222' , confirmation_date = '2019-12-12' , comments = 'nothing...'
where email = 'peter@gmail.com';

select distinct full_name from rsvp;

select count(*)  from rsvp;





