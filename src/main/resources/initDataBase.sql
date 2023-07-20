create table if not exists Employees (
    id serial primary key not null,
    name varchar(100) not null unique,
    DOB date
);

create table if not exists Problems (
    id serial primary key not null,
    name varchar(100) not null,
    deadline date not null,
    description varchar(255),
    type varchar(255),
    employeeId serial not null, foreign key (employeeId) references Employees(id)
);