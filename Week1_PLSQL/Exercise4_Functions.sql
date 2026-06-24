-- Exercise 4: Functions

-- Scenario 1: Calculate the age of customers for eligibility checks.
CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
) RETURN NUMBER IS
    v_age NUMBER;
BEGIN
    IF p_dob IS NULL THEN
        RETURN NULL;
    END IF;
    -- Calculate age in years
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END;
/

-- Scenario 2: Compute the monthly installment for a loan.
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount    IN NUMBER,
    p_interest_rate  IN NUMBER, -- Annual percentage interest rate (e.g. 5 for 5%)
    p_duration_years IN NUMBER
) RETURN NUMBER IS
    v_monthly_rate NUMBER;
    v_total_months NUMBER;
    v_installment  NUMBER;
BEGIN
    -- Validations
    IF p_loan_amount <= 0 OR p_interest_rate < 0 OR p_duration_years <= 0 THEN
        RETURN 0;
    END IF;
    
    -- If interest rate is 0, installment is simple division
    IF p_interest_rate = 0 THEN
        RETURN ROUND(p_loan_amount / (p_duration_years * 12), 2);
    END IF;

    -- Monthly interest rate
    v_monthly_rate := (p_interest_rate / 100) / 12;
    -- Total payments
    v_total_months := p_duration_years * 12;
    
    -- Formula: PMT = P * r * (1 + r)^n / ((1 + r)^n - 1)
    v_installment := p_loan_amount * (v_monthly_rate * POWER(1 + v_monthly_rate, v_total_months)) / (POWER(1 + v_monthly_rate, v_total_months) - 1);
    
    RETURN ROUND(v_installment, 2);
END;
/

-- Scenario 3: Check if a customer has sufficient balance before making a transaction.
CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    IF p_amount < 0 THEN
        RETURN FALSE;
    END IF;

    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    IF v_balance >= p_amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
    WHEN OTHERS THEN
        RETURN FALSE;
END;
/

-- Utility wrapper for testing HasSufficientBalance in SQL queries
CREATE OR REPLACE FUNCTION HasSufficientBalanceSQL (
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN VARCHAR2 IS
BEGIN
    IF HasSufficientBalance(p_account_id, p_amount) THEN
        RETURN 'TRUE';
    ELSE
        RETURN 'FALSE';
    END IF;
END;
/
