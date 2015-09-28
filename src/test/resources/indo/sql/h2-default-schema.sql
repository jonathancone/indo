CREATE TABLE payroll_detail (
  payroll_id IDENTITY PRIMARY KEY,
  election_1 TINYINT,
  election_2 SMALLINT,
  election_3 INT,
  election_4 BIGINT,
  election_5 DOUBLE,
  election_6 REAL,
  election_7 BINARY
);

CREATE TABLE employee (
  employee_id    IDENTITY PRIMARY KEY,
  first_name     VARCHAR(255) NOT NULL,
  last_name      VARCHAR(255) NOT NULL,
  active         BOOLEAN,
  hire_date      DATE,
  departure_date DATE,
  payroll_id     INT,
  salary         DECIMAL(20, 2)
);

CREATE TABLE department (
  department_id IDENTITY PRIMARY KEY,
  title         VARCHAR(255),
  description   VARCHAR(255),
  manager_id    IDENTITY,
  FOREIGN KEY (manager_id) REFERENCES employee (employee_id)
);

CREATE TABLE manager (
  employee_id   IDENTITY,
  department_id IDENTITY,
  PRIMARY KEY (employee_id, department_id)
);

CREATE TABLE shift (
  shift_id   IDENTITY PRIMARY KEY,
  start_date DATE,
  end_date   DATE,
  start_time TIME,
  end_time   TIME
);

CREATE TABLE employee_shift (
  shift_id    IDENTITY,
  employee_id IDENTITY,
  FOREIGN KEY (shift_id) REFERENCES shift (shift_id),
  FOREIGN KEY (employee_id) REFERENCES shift (employee_id),
  PRIMARY KEY (shift_id, employee_id)
);


INSERT INTO payroll_detail VALUES (1, 11, 11, 11, 11, 11.1, 11.1, 1);
INSERT INTO payroll_detail VALUES (2, 22, 22, 22, 22, 22.2, 22.2, 1);
INSERT INTO payroll_detail VALUES (3, 33, 33, 33, 33, 33.3, 33.3, 1);
INSERT INTO payroll_detail VALUES (4, 44, 44, 44, 44, 44.4, 44.4, 1);

INSERT INTO employee VALUES (1, 'Laserbeak', 'Onslaught', 1, '2010-10-25', NULL, 1, 19700.00);
INSERT INTO employee VALUES (2, 'Razorclaw', 'Arcana', 2, '2001-01-21', NULL, 1, 22000.00);
INSERT INTO employee VALUES (3, 'Nightstick', 'Strip', 3, '2007-06-04', '2007-06-05', 1, 42030.00);
INSERT INTO employee VALUES (4, 'Barrage', 'Sludge', 4, '2012-08-19', '2014-04-18', 1, 66510.00);

INSERT INTO department VALUES (101, 'Mergers', 'The Mergers department.', 1);
INSERT INTO department VALUES (201, 'Human Resources', 'The Mergers department.', 2);

INSERT INTO manager VALUES (1, 101);
INSERT INTO manager VALUES (2, 201);

INSERT INTO shift VALUES (500, '2012-04-01', '2012-04-30', '22:30:00', '06:30:00');
INSERT INTO shift VALUES (600, '2012-05-01', '2012-05-30', '01:00:00', '09:45:00');

INSERT INTO employee_shift VALUES (500, 3);
INSERT INTO employee_shift VALUES (500, 4);