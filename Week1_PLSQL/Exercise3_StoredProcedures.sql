-- Exercise 3: Stored Procedures

-- Scenario 1: Process monthly interest for all savings accounts.
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
    v_interest_rate CONSTANT NUMBER := 0.01; -- 1% interest rate
    v_updated_count NUMBER := 0;
BEGIN
    -- Update all savings accounts balance by applying 1% interest
    UPDATE Accounts
    SET Balance = Balance * (1 + v_interest_rate),
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';
    
    v_updated_count := SQL%ROWCOUNT;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Interest processed for ' || v_updated_count || ' savings accounts.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
END;
/

-- Scenario 2: Implement a bonus scheme for employees based on their performance.
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN VARCHAR2,
    p_bonus_pct  IN NUMBER
) IS
    v_updated_count NUMBER := 0;
BEGIN
    IF p_bonus_pct < 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Bonus percentage cannot be negative.');
    END IF;

    -- Update salary of employees in the department
    UPDATE Employees
    SET Salary = Salary * (1 + p_bonus_pct / 100)
    WHERE Department = p_department;
    
    v_updated_count := SQL%ROWCOUNT;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus of ' || p_bonus_pct || '% applied to ' || v_updated_count || ' employees in ' || p_department || ' department.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
END;
/

-- Scenario 3: Transfer funds between customer accounts (normal procedure with validation).
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account_id IN NUMBER,
    p_to_account_id   IN NUMBER,
    p_amount          IN NUMBER
) IS
    v_from_balance NUMBER;
BEGIN
    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Amount to transfer must be positive.');
    END IF;

    -- Fetch and lock the source account balance
    SELECT Balance INTO v_from_balance
    FROM Accounts
    WHERE AccountID = p_from_account_id
    FOR UPDATE;

    -- Validate balance
    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20005, 'Insufficient balance. Source account has $' || v_from_balance || ', trying to transfer $' || p_amount);
    END IF;

    -- Check if destination account exists
    DECLARE
        v_dummy NUMBER;
    BEGIN
        SELECT 1 INTO v_dummy FROM Accounts WHERE AccountID = p_to_account_id;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20006, 'Destination account ' || p_to_account_id || ' does not exist.');
    END;

    -- Deduct from source
    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from_account_id;

    -- Add to destination
    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to_account_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Successfully transferred $' || p_amount || ' from account ' || p_from_account_id || ' to ' || p_to_account_id);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Source account ' || p_from_account_id || ' not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transaction failed: ' || SQLERRM);
END;
/
