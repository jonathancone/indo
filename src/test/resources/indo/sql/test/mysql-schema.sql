DROP TABLE IF EXISTS timecard;
DROP TABLE IF EXISTS employee_shift;
DROP TABLE IF EXISTS shift;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department (
  department_id INT PRIMARY KEY AUTO_INCREMENT,
  name          VARCHAR(255),
  manager_id    BIGINT
);

CREATE TABLE employee (
  employee_id    BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name     VARCHAR(255) NOT NULL,
  last_name      VARCHAR(255) NOT NULL,
  active         BOOLEAN,
  hire_date      DATE,
  salary         DECIMAL(20, 2),
  department_id  INT,
  FOREIGN KEY (department_id) REFERENCES department (department_id)
);

CREATE TABLE shift (
  shift_id   BIGINT PRIMARY KEY AUTO_INCREMENT,
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

CREATE TABLE timecard (
  timecard_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
  employee_id  BIGINT,
  week_of_year INT,
  actual_hours INT,
  FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

