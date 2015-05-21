@echo off 
net stop tomcat7
call mvn clean package 
rmdir /s/q "D:\Programmation\tomcat\webapps\app-01-test"
move /y target\app-01-test-1.0.war "D:\Programmation\tomcat\webapps\app-01-test.war"
net start tomcat7
