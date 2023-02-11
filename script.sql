select * from student;
select * from student where age > 9 and age < 13;
select name from student;
select * from student where name like '%o%';
select * from student where age < id;
select * from student order by age;

select * from faculty, student
where faculty.id = faculty_id
;






