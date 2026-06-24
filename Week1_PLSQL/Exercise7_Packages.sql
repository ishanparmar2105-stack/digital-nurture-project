-- Exercise 7: Packages

-- ====================================================================
-- Package 1: CustomerManagement
-- ====================================================================
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(
        p_customer_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_dob         IN DATE,
        p_balance     IN NUMBER
    );
    
    PROCEDURE UpdateCustomer(
        p_customer_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_balance     IN NUMBER
    );
    
    FUNCTION GetCustomerBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer(
        p_customer_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_dob         IN DATE,
        p_balance     IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Added Customer: ' || p_name || ' (ID: ' || p_customer_id || ')');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_customer_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error adding customer: ' || SQLERRM);
    END AddCustomer;

    PROCEDURE UpdateCustomer(
        p_customer_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_balance     IN NUMBER
    ) IS
    BEGIN
        UPDATE Customers
        SET Name = p_name,
            Balance = p_balance,
            LastModified = SYSDATE
        WHERE CustomerID = p_customer_id;
        
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_customer_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Updated Customer details for ID: ' || p_customer_id);
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error updating customer: ' || SQLERRM);
    END UpdateCustomer;

    FUNCTION GetCustomerBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER IS
        v_balance NUMBER := 0;
    BEGIN
        SELECT Balance INTO v_balance
        FROM Customers
        WHERE CustomerID = p_customer_id;
        
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
        WHEN OTHERS THEN
            RETURN NULL;
    END GetCustomerBalance;
END CustomerManagement;
/


-- ====================================================================
-- Package 2: EmployeeManagement
-- ====================================================================
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_employee_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_position    IN VARCHAR2,
        p_salary      IN NUMBER,
        p_department  IN VARCHAR2,
        p_hire_date   IN DATE
    );
    
    PROCEDURE UpdateEmployee(
        p_employee_id IN NUMBER,
        p_position    IN VARCHAR2,
        p_salary      IN NUMBER
    );
    
    FUNCTION CalculateAnnualSalary(
        p_employee_id IN NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_employee_id IN NUMBER,
        p_name        IN VARCHAR2,
        p_position    IN VARCHAR2,
        p_salary      IN NUMBER,
        p_department  IN VARCHAR2,
        p_hire_date   IN DATE
    ) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_employee_id, p_name, p_position, p_salary, p_department, p_hire_date);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Hired Employee: ' || p_name || ' (ID: ' || p_employee_id || ')');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_employee_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error hiring employee: ' || SQLERRM);
    END HireEmployee;

    PROCEDURE UpdateEmployee(
        p_employee_id IN NUMBER,
        p_position    IN VARCHAR2,
        p_salary      IN NUMBER
    ) IS
    BEGIN
        UPDATE Employees
        SET Position = p_position,
            Salary = p_salary
        WHERE EmployeeID = p_employee_id;
        
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_employee_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Updated Employee ID: ' || p_employee_id);
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error updating employee: ' || SQLERRM);
    END UpdateEmployee;

    FUNCTION CalculateAnnualSalary(
        p_employee_id IN NUMBER
    ) RETURN NUMBER IS
        v_salary NUMBER := 0;
    BEGIN
        SELECT Salary INTO v_salary
        FROM Employees
        WHERE EmployeeID = p_employee_id;
        
        -- Annual salary is Monthly Salary * 12
        RETURN v_salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
        WHEN OTHERS THEN
            RETURN NULL;
    END CalculateAnnualSalary;
END EmployeeManagement;
/


-- ====================================================================
-- Package 3: AccountOperations
-- ====================================================================
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(
        p_account_id   IN NUMBER,
        p_customer_id  IN NUMBER,
        p_account_type IN VARCHAR2,
        p_balance      IN NUMBER
    );
    
    PROCEDURE CloseAccount(
        p_account_id IN NUMBER
    );
    
    FUNCTION GetTotalCustomerBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(
        p_account_id   IN NUMBER,
        p_customer_id  IN NUMBER,
        p_account_type IN VARCHAR2,
        p_balance      IN NUMBER
    ) IS
        v_cust_exists NUMBER := 0;
    BEGIN
        -- Check customer exists
        SELECT COUNT(*) INTO v_cust_exists FROM Customers WHERE CustomerID = p_customer_id;
        IF v_cust_exists = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_customer_id || ' does not exist. Cannot open account.');
            RETURN;
        END IF;

        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Opened Account: ' || p_account_id || ' (' || p_account_type || ') for Customer ID: ' || p_customer_id);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Account ID ' || p_account_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error opening account: ' || SQLERRM);
    END OpenAccount;

    PROCEDURE CloseAccount(
        p_account_id IN NUMBER
    ) IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID = p_account_id;
        
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Error: Account ID ' || p_account_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Closed Account ID: ' || p_account_id);
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error closing account: ' || SQLERRM);
    END CloseAccount;

    FUNCTION GetTotalCustomerBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER IS
        v_total_balance NUMBER := 0;
    BEGIN
        -- Sum up balance across all accounts of the customer
        SELECT SUM(Balance) INTO v_total_balance
        FROM Accounts
        WHERE CustomerID = p_customer_id;
        
        RETURN NVL(v_total_balance, 0);
    EXCEPTION
        WHEN OTHERS THEN
            RETURN 0;
    END GetTotalCustomerBalance;
END AccountOperations;
/
