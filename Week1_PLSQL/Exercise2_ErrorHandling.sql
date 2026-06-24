-- Exercise 2: Error Handling

-- Scenario 1: Handle exceptions during fund transfers between accounts.
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account_id IN NUMBER,
    p_to_account_id   IN NUMBER,
    p_amount          IN NUMBER
) IS
    v_from_balance NUMBER;
    v_to_balance   NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    -- Check negative or zero amount
    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Transfer amount must be greater than zero.');
    END IF;

    -- Lock and check from account balance
    SELECT Balance INTO v_from_balance 
    FROM Accounts 
    WHERE AccountID = p_from_account_id 
    FOR UPDATE;
    
    -- Check to account existence
    SELECT Balance INTO v_to_balance 
    FROM Accounts 
    WHERE AccountID = p_to_account_id 
    FOR UPDATE;

    IF v_from_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    -- Perform transfer
    UPDATE Accounts 
    SET Balance = Balance - p_amount 
    WHERE AccountID = p_from_account_id;
    
    UPDATE Accounts 
    SET Balance = Balance + p_amount 
    WHERE AccountID = p_to_account_id;

    -- Record transaction (Debited)
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (Transactions_Seq.NEXTVAL, p_from_account_id, SYSDATE, p_amount, 'Withdrawal');
    
    -- Record transaction (Credited)
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (Transactions_Seq.NEXTVAL, p_to_account_id, SYSDATE, p_amount, 'Deposit');

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Successfully transferred $' || p_amount || ' from account ' || p_from_account_id || ' to account ' || p_to_account_id);

EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('SafeTransferFunds', 'Insufficient funds in account ' || p_from_account_id || ' for transfer of $' || p_amount);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds. Transaction rolled back and logged.');
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('SafeTransferFunds', 'One or both accounts do not exist: ' || p_from_account_id || ', ' || p_to_account_id);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Account not found. Transaction rolled back and logged.');
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('SafeTransferFunds', 'SQL Error Code: ' || SQLCODE || ' - ' || SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM || '. Transaction rolled back.');
END;
/

-- Scenario 2: Manage errors when updating employee salaries.
CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id IN NUMBER,
    p_percentage  IN NUMBER
) IS
    v_current_salary NUMBER;
BEGIN
    IF p_percentage < -100 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Salary reduction percentage cannot be less than -100%.');
    END IF;

    -- Fetch employee to check existence
    SELECT Salary INTO v_current_salary 
    FROM Employees 
    WHERE EmployeeID = p_employee_id 
    FOR UPDATE;

    UPDATE Employees
    SET Salary = Salary * (1 + p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for Employee ID ' || p_employee_id || '.');
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('UpdateSalary', 'Employee ID ' || p_employee_id || ' does not exist.');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_employee_id || ' not found. Logged to ErrorLog.');
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('UpdateSalary', 'SQL Error Code: ' || SQLCODE || ' - ' || SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error updating salary: ' || SQLERRM);
END;
/

-- Scenario 3: Ensure data integrity when adding a new customer.
CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name        IN VARCHAR2,
    p_dob         IN DATE,
    p_balance     IN NUMBER
) IS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Successfully added customer: ' || p_name || ' with ID ' || p_customer_id);

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('AddNewCustomer', 'Duplicate ID error: Customer with ID ' || p_customer_id || ' already exists.');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_customer_id || ' already exists. Logged to ErrorLog.');
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage)
        VALUES ('AddNewCustomer', 'SQL Error Code: ' || SQLCODE || ' - ' || SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error adding customer: ' || SQLERRM);
END;
/
