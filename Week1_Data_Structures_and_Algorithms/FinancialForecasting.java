/*
 * Exercise 7: Financial Forecasting
 * 
 * Recursion:
 * - A programming technique where a method calls itself to solve a smaller instance of the same problem.
 * - Requires a base case to terminate recursion, and a recursive step to move towards the base case.
 * 
 * Time Complexity of Simple Recursive Growth Calculation:
 * - O(N) where N is the number of periods/years, since we call the function once per year.
 * - Space complexity is O(N) due to the call stack.
 * 
 * Optimization:
 * - Iterative approach: reduces space complexity to O(1) by avoiding the call stack.
 * - Memoization: useful if there are overlapping subproblems (not directly applicable to simple linear growth, 
 *   but essential for tree-like recursive models like option pricing).
 */

public class FinancialForecasting {

    // Recursive method to calculate future value
    // Formula: FV = PV * (1 + growthRate)^years
    // Recursively: FV(years) = FV(years - 1) * (1 + growthRate)
    public static double calculateFutureValue(double currentValue, double growthRate, int years) {
        // Base case: if years is 0, future value is the current value
        if (years == 0) {
            return currentValue;
        }
        // Recursive case
        return calculateFutureValue(currentValue, growthRate, years - 1) * (1 + growthRate);
    }

    // Optimized Iterative Method (O(1) auxiliary space)
    public static double calculateFutureValueIterative(double currentValue, double growthRate, int years) {
        double futureValue = currentValue;
        for (int i = 0; i < years; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }

    public static void main(String[] args) {
        System.out.println("=== Financial Forecasting Tool ===\n");

        double initialInvestment = 1000.00; // Present Value (PV)
        double annualGrowthRate = 0.05;    // 5% growth rate
        int forecastingPeriod = 10;        // 10 years

        System.out.println("Initial Investment: $" + initialInvestment);
        System.out.println("Annual Growth Rate: " + (annualGrowthRate * 100) + "%");
        System.out.println("Forecast Period: " + forecastingPeriod + " years");

        // Recursive Calculation
        long startRec = System.nanoTime();
        double futureValueRec = calculateFutureValue(initialInvestment, annualGrowthRate, forecastingPeriod);
        long endRec = System.nanoTime();
        
        System.out.println("\n--- Recursive Approach ---");
        System.out.printf("Predicted Future Value: $%.2f%n", futureValueRec);
        System.out.println("Time taken: " + (endRec - startRec) + " ns");

        // Iterative Calculation
        long startIter = System.nanoTime();
        double futureValueIter = calculateFutureValueIterative(initialInvestment, annualGrowthRate, forecastingPeriod);
        long endIter = System.nanoTime();

        System.out.println("\n--- Iterative Approach (Optimized) ---");
        System.out.printf("Predicted Future Value: $%.2f%n", futureValueIter);
        System.out.println("Time taken: " + (endIter - startIter) + " ns");

        System.out.println("\n--- Analysis & Optimization ---");
        System.out.println("Recursive Complexity: Time O(N), Space O(N) due to call stack.");
        System.out.println("Iterative Complexity: Time O(N), Space O(1) - prevents StackOverflowError for large N.");
    }
}
