create table if not exists Employees (
                                         id bigint not null primary key,
                                         name varchar(100),
                                         DOB date
);

create table if not exists Problems (
                                        id bigint not null primary key,
                                        name varchar(500),
                                        deadline date,
                                        description varchar(1000),
                                        type varchar(1000),
                                        employeeId bigint references Employees(id)
);