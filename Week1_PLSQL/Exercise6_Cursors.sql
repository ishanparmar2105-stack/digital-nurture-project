-- Exercise 6: Cursors

-- Scenario 1: Generate monthly statements for all customers.
DECLARE
    -- Cursor to fetch all transactions for the current month along with customer details
    CURSOR c_monthly_transactions IS
        SELECT c.Name, a.AccountID, t.TransactionID, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE)
        ORDER BY c.Name, t.TransactionDate;
        
    v_rec c_monthly_transactions%ROWTYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('============================================================');
    DBMS_OUTPUT.PUT_LINE('                 MONTHLY STATEMENTS REPORT                  ');
    DBMS_OUTPUT.PUT_LINE('============================================================');
    
    OPEN c_monthly_transactions;
    LOOP
        FETCH c_monthly_transactions INTO v_rec;
        EXIT WHEN c_monthly_transactions%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('Customer: ' || v_rec.Name || 
                             ' | Account: ' || v_rec.AccountID || 
                             ' | Txn ID: ' || v_rec.TransactionID || 
                             ' | Date: ' || TO_CHAR(v_rec.TransactionDate, 'YYYY-MM-DD') || 
                             ' | Type: ' || v_rec.TransactionType || 
                             ' | Amount: $' || v_rec.Amount);
    END LOOP;
    CLOSE c_monthly_transactions;
    DBMS_OUTPUT.PUT_LINE('============================================================');
END;
/

-- Scenario 2: Apply annual fee to all accounts.
DECLARE
    -- Constant for annual fee
    v_annual_fee CONSTANT NUMBER := 50.00;
    
    -- Cursor to fetch all accounts for update
    CURSOR c_accounts IS
        SELECT AccountID, Balance 
        FROM Accounts
        FOR UPDATE;
        
    v_account_id Accounts.AccountID%TYPE;
    v_balance    Accounts.Balance%TYPE;
BEGIN
    OPEN c_accounts;
    LOOP
        FETCH c_accounts INTO v_account_id, v_balance;
        EXIT WHEN c_accounts%NOTFOUND;
        
        -- Deduct fee
        UPDATE Accounts
        SET Balance = Balance - v_annual_fee,
            LastModified = SYSDATE
        WHERE CURRENT OF c_accounts;
        
        DBMS_OUTPUT.PUT_LINE('Deducted $' || v_annual_fee || ' annual fee from Account ID: ' || v_account_id || '. New Balance: $' || (v_balance - v_annual_fee));
    END LOOP;
    CLOSE c_accounts;
    COMMIT;
END;
/

-- Scenario 3: Update the interest rate for all loans based on a new policy.
DECLARE
    -- Let's say the new policy increases the interest rate by 0.5% for all existing loans
    v_rate_increment CONSTANT NUMBER := 0.5;
    
    CURSOR c_loans IS
        SELECT LoanID, InterestRate, CustomerID
        FROM Loans
        FOR UPDATE;
        
    v_loan_rec c_loans%ROWTYPE;
BEGIN
    OPEN c_loans;
    LOOP
        FETCH c_loans INTO v_loan_rec;
        EXIT WHEN c_loans%NOTFOUND;
        
        -- Update interest rate
        UPDATE Loans
        SET InterestRate = InterestRate + v_rate_increment
        WHERE CURRENT OF c_loans;
        
        DBMS_OUTPUT.PUT_LINE('Updated Loan ID ' || v_loan_rec.LoanID || ' (Customer ID ' || v_loan_rec.CustomerID || '): Interest Rate ' || v_loan_rec.InterestRate || '% -> ' || (v_loan_rec.InterestRate + v_rate_increment) || '%');
    END LOOP;
    CLOSE c_loans;
    COMMIT;
END;
/
