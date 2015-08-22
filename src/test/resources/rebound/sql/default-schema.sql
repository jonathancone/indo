CREATE TABLE Employee (
    employeeId IDENTITY PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    active BOOLEAN,
    hireDate DATE,
    departureDate DATE,
    payrollId INT,
    salary DECIMAL(20,2)
);
