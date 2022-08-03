select * from users;
select count(id) from users;

select distinct count(full_name) from users
where full_name is not null;


