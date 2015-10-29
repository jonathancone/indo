DROP TABLE IF EXISTS employee_shift;
DROP TABLE IF EXISTS shift;
DROP TABLE IF EXISTS manager;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS payroll_detail;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS payroll_detail;

CREATE TABLE payroll_detail (
   payroll_id SERIAL PRIMARY KEY,
   election_1 SMALLINT,
   election_2 SMALLINT,
   election_3 INT,
   election_4 BIGINT,
   election_5 DOUBLE PRECISION,
   election_6 REAL,
   election_7 BYTEA
 );

 CREATE TABLE employee (
   employee_id    SERIAL PRIMARY KEY,
   first_name     VARCHAR(255) NOT NULL,
   last_name      VARCHAR(255) NOT NULL,
   active         BOOLEAN,
   hire_date      DATE,
   departure_date DATE,
   payroll_id     BIGINT,
   salary         DECIMAL(20, 2)
 );

 CREATE TABLE department (
   department_id SERIAL PRIMARY KEY,
   title         VARCHAR(255),
   description   VARCHAR(255),
   manager_id    BIGINT,
   FOREIGN KEY (manager_id) REFERENCES employee (employee_id)
 );

 CREATE TABLE manager (
   employee_id   BIGINT,
   department_id BIGINT,
   PRIMARY KEY (employee_id, department_id)
 );

 CREATE TABLE shift (
   shift_id   SERIAL PRIMARY KEY,
   start_date DATE,
   end_date   DATE,
   start_time TIME,
   end_time   TIME
 );

 CREATE TABLE employee_shift (
   shift_id    BIGINT,
   employee_id BIGINT,
   FOREIGN KEY (shift_id) REFERENCES shift (shift_id),
   FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
   PRIMARY KEY (shift_id, employee_id)
 );


 -- INSERT INTO payroll_detail VALUES (1, 11, 11, 11, 11, 11.1, 11.1, 1);
 -- INSERT INTO payroll_detail VALUES (2, 22, 22, 22, 22, 22.2, 22.2, 1);
 -- INSERT INTO payroll_detail VALUES (3, 33, 33, 33, 33, 33.3, 33.3, 1);
 -- INSERT INTO payroll_detail VALUES (4, 44, 44, 44, 44, 44.4, 44.4, 1);
 --
 -- INSERT INTO employee VALUES (1, 'Laser', 'Beam', 1, '2010-10-25', NULL, 1, 19700.00);
 -- INSERT INTO employee VALUES (2, 'Razor', 'Claw', 2, '2001-01-21', NULL, 1, 22000.00);
 -- INSERT INTO employee VALUES (3, 'Night', 'Stick', 3, '2007-06-04', '2007-06-05', 1, 42030.00);
 -- INSERT INTO employee VALUES (4, 'Barrage', 'Sludge', 4, '2012-08-19', '2014-04-18', 1, 66510.00);
 --
 -- INSERT INTO department VALUES (101, 'Mergers', 'The Mergers department.', 1);
 -- INSERT INTO department VALUES (201, 'Human Resources', 'The Mergers department.', 2);
 --
 -- INSERT INTO manager VALUES (1, 101);
 -- INSERT INTO manager VALUES (2, 201);
 --
 -- INSERT INTO shift VALUES (500, '2012-04-01', '2012-04-30', '22:30:00', '06:30:00');
 -- INSERT INTO shift VALUES (600, '2012-05-01', '2012-05-30', '01:00:00', '09:45:00');
 --
 -- INSERT INTO employee_shift VALUES (500, 3);
 -- INSERT INTO employee_shift VALUES (500, 4);