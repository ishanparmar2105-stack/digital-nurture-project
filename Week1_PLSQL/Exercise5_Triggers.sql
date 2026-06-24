-- Exercise 5: Triggers

-- Scenario 1: Automatically update the last modified date when a customer's record is updated.
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :new.LastModified := SYSDATE;
END;
/

-- Scenario 2: Maintain an audit log for all transactions.
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (:new.TransactionID, :new.AccountID, :new.TransactionDate, :new.Amount, :new.TransactionType);
END;
/

-- Scenario 3: Enforce business rules on deposits and withdrawals.
-- Ensures withdrawals do not exceed the balance and deposits are positive before inserting.
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    -- Rule 1: Deposits must be positive
    IF :new.TransactionType = 'Deposit' AND :new.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20007, 'Deposit amount must be positive.');
    END IF;

    -- Rule 2: Withdrawals must be positive
    IF :new.TransactionType = 'Withdrawal' AND :new.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20008, 'Withdrawal amount must be positive.');
    END IF;

    -- Rule 3: Check sufficient balance for withdrawals
    IF :new.TransactionType = 'Withdrawal' THEN
        -- Fetch current balance of the account
        SELECT Balance INTO v_balance
        FROM Accounts
        WHERE AccountID = :new.AccountID;

        IF v_balance < :new.Amount THEN
            RAISE_APPLICATION_ERROR(-20009, 'Insufficient balance. Account balance is $' || v_balance || ', requested withdrawal is $' || :new.Amount);
        END IF;
        
        -- Automatically update account balance (optional but common in systems, or let the transaction handle it)
        -- To avoid double updates if a procedure also does it, triggers usually just check rules.
        -- We will just check rules here as requested: "ensures withdrawals do not exceed balance..."
    END IF;
END;
/
