-- Sample Data Insertion Script for PL/SQL Exercises

-- Clear existing data
DELETE FROM Transactions;
DELETE FROM Loans;
DELETE FROM Accounts;
DELETE FROM Customers;
DELETE FROM Employees;
DELETE FROM AuditLog;
DELETE FROM ErrorLog;

-- Customers
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'), 12000, SYSDATE, 'FALSE'); -- Age > 60 in 2026, Balance > 10000

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE, 'FALSE'); -- Age < 60, Balance < 10000

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
VALUES (3, 'Robert Miller', TO_DATE('1948-11-10', 'YYYY-MM-DD'), 9500, SYSDATE, 'FALSE'); -- Age > 60, Balance < 10000

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
VALUES (4, 'Emily Davis', TO_DATE('1982-03-05', 'YYYY-MM-DD'), 25000, SYSDATE, 'FALSE'); -- Age < 60, Balance > 10000

-- Accounts
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (1, 1, 'Savings', 12000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (2, 2, 'Checking', 1500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (3, 3, 'Savings', 9500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (4, 4, 'Savings', 25000, SYSDATE);

-- Transactions
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (101, 1, SYSDATE - 5, 200, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (102, 2, SYSDATE - 2, 300, 'Withdrawal');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (103, 3, SYSDATE, 500, 'Deposit');

-- Loans
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (1, 1, 5000, 5, SYSDATE - 10, ADD_MONTHS(SYSDATE, 12)); -- Due in future

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (2, 2, 10000, 6, SYSDATE - 350, SYSDATE + 15); -- Due in next 15 days (next 30 days)

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (3, 3, 2000, 7.5, SYSDATE - 180, SYSDATE + 5); -- Due in next 5 days

-- Employees
INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (3, 'Charlie Green', 'Developer', 65000, 'IT', TO_DATE('2019-10-01', 'YYYY-MM-DD'));

COMMIT;
