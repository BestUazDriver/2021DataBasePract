create table course
(
    id             serial primary key,
    name           varchar(20),
    dateofbeginend varchar(20),
    teacherid      integer
);


insert into course (name, teacherid)
values ('1st', 1);
insert into course (name, teacherid)
values ('2nd', 1);
insert into course (name, teacherid)
values ('3rd', 2);

drop table course;

create table lesson
(
    id          serial primary key,
    lName       varchar(20),
    timeAndWeek varchar(20),
    courseId    integer,
    foreign key (courseId) references course(id)
);

drop table lesson;

create table student
(
    id       serial primary key,
    name     varchar(20),
    surname  varchar(20),
    groupnum integer
);

insert into student (name, surname, groupnum) VALUES ('dan', 'white',1);
insert into student (name, surname, groupnum) VALUES ('daniel', 'green',1);
insert into student (name, surname, groupnum) VALUES ('dan', 'black',2);
create table teacher
(
    id         serial primary key,
    name       varchar(20),
    surname    varchar(20),
    experience integer,
    foreign key (id) references course(id)
);

drop table teacher;

drop table student;

create table courseToStudent
(
    student_id integer,
    course_id  integer,
    foreign key (student_id) references student (id),
    foreign key (course_id) references course (id)
);

create table teacherToCourse(
                                teacher_id  integer,
                                course_id integer,
                                foreign key (teacher_id) references teacher(id),
                                foreign key (course_id) references course(id)

);
drop table courseToStudent;

