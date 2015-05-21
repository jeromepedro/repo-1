create table projects (id int primary key auto_increment, name varchar(256)); 
create table prj_users (id int primary key auto_increment, project_id int, cuid varchar(8), role_id varchar(3), constraint fk_projectid foreign key (project_id) references projects(id)); 
create table scheduler (last_exec_time datetime);
insert into scheduler (last_exec_time) values ('2014-01-01 12:30:00'); 
