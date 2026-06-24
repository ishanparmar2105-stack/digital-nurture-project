-- Exercise 1: Control Structures

-- Scenario 1: Apply a 1% discount to loan interest rates for customers above 60 years old.
DECLARE
    v_customer_id Customers.CustomerID%TYPE;
    v_dob Customers.DOB%TYPE;
    v_age NUMBER;
    
    CURSOR c_customers IS
        SELECT CustomerID, DOB FROM Customers;
BEGIN
    FOR rec IN c_customers LOOP
        -- Calculate age in years
        v_age := MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12;
        
        IF v_age > 60 THEN
            -- Apply 1% discount (e.g., subtract 1 from InterestRate)
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = rec.CustomerID;
            
            DBMS_OUTPUT.PUT_LINE('Applied 1% discount to customer ID: ' || rec.CustomerID || ' (Age: ' || ROUND(v_age) || ')');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Promote a customer to VIP status based on their balance.
DECLARE
    CURSOR c_vip_candidates IS
        SELECT CustomerID, Balance FROM Customers;
BEGIN
    FOR rec IN c_vip_candidates LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = rec.CustomerID;
            DBMS_OUTPUT.PUT_LINE('Customer ' || rec.CustomerID || ' promoted to VIP (Balance: $' || rec.Balance || ')');
        ELSE
            UPDATE Customers
            SET IsVIP = 'FALSE'
            WHERE CustomerID = rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Send reminders to customers whose loans are due within the next 30 days.
DECLARE
    v_customer_name Customers.Name%TYPE;
    v_loan_id Loans.LoanID%TYPE;
    v_end_date Loans.EndDate%TYPE;
    
    CURSOR c_due_loans IS
        SELECT l.LoanID, l.EndDate, c.Name
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Loan Due Reminders (Next 30 Days) ---');
    FOR rec IN c_due_loans LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Customer ' || rec.Name || ' (Loan ID: ' || rec.LoanID || ') has a loan ending on ' || TO_CHAR(rec.EndDate, 'YYYY-MM-DD') || '.');
    END LOOP;
END;
/
