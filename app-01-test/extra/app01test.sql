create database app01test;
go 
use app01test;
create table dbo.projects (id int IDENTITY(1,1) primary key, name varchar(256)); 
create table dbo.prj_users (id int IDENTITY(1,1) primary key, project_id int, cuid varchar(8), role_id varchar(3), constraint fk_projectid foreign key (project_id) references projects(id)); 
create table scheduler (last_exec_time datetime);
insert into scheduler (last_exec_time) values ('2014-01-01 12:30:00'); 

