# Cognizant Digital Nurture Program - Java Full Stack (Java FSE)
## Week 1: Core Backend Foundations & Best Practices

Welcome to the Week 1 submission for the Cognizant Digital Nurture 7-Week Java Full Stack Deepskilling Program. This repository contains the complete implementation and test suites for all assigned topics under Week 1.

---

## 📂 Table of Contents
1. [Design Patterns & Principles](#-1-design-patterns--principles)
2. [Data Structures & Algorithms](#-2-data-structures--algorithms)
3. [PL/SQL Programming](#-3-plsql-programming)
4. [JUnit & Mockito Testing](#-4-junit--mockito-testing)
5. [SLF4J & Logback Logging](#-5-slf4j--logback-logging)

---

## 🛠 1. Design Patterns & Principles
**Directory:** [`Week1_Design_Patterns_and_Principles`](./Week1_Design_Patterns_and_Principles)

This module implements 11 design patterns divided into Creational, Structural, and Behavioral categories, as well as SOLID principles:

*   **Creational Patterns:**
    *   `SingletonPatternExample.java`: Thread-safe, lazy-initialized double-checked locking logger.
    *   `FactoryMethodPatternExample.java`: Document management system supporting Word, PDF, and Excel factory methods.
    *   `BuilderPatternExample.java`: Step-by-step object construction of a complex Computer configuration.
*   **Structural Patterns:**
    *   `AdapterPatternExample.java`: Integration of Stripe, PayPal, and Square payment gateways under a unified processor.
    *   `DecoratorPatternExample.java`: Dynamic wrapper notifications extending Email with SMS and Slack features.
    *   `ProxyPatternExample.java`: RealImage viewing using lazy loading and caching proxies.
*   **Behavioral Patterns:**
    *   `ObserverPatternExample.java`: Real-time stock price push updates to mobile and web observer clients.
    *   `StrategyPatternExample.java`: Dynamically choosing Credit Card, PayPal, or Bitcoin payment algorithms.
    *   `CommandPatternExample.java`: Home automation receiver invoking light toggle commands.
    *   `MVCPatternExample.java`: Clear separation of Student Model, View, and Controller.
    *   `DependencyInjectionExample.java`: Constructor-injected Customer database repository service.

### How to Compile & Run:
```bash
cd Week1_Design_Patterns_and_Principles
javac *.java
java SingletonPatternExample
```

---

## 🚀 2. Data Structures & Algorithms
**Directory:** [`Week1_Data_Structures_and_Algorithms`](./Week1_Data_Structures_and_Algorithms)

Hands-on implementation of core algorithms and comparison of time/space complexities:

1.  `InventoryManagement.java`: O(1) average lookup, insertion, and deletion using a HashMap.
2.  `EcommerceSearch.java`: O(n) Linear Search vs O(log n) Binary Search on sorted products.
3.  `SortingCustomerOrders.java`: O(n²) Bubble Sort vs O(n log n) Quick Sort for order ranking.
4.  `EmployeeManagement.java`: Array-based storage demonstrating insertion (O(1)) and shifting deletion (O(n)).
5.  `TaskManagement.java`: Custom Singly Linked List for tasks (add, traverse, search, delete).
6.  `LibraryManagement.java`: Book indexing with Linear and Binary Search comparison.
7.  `FinancialForecasting.java`: Recursive predictions with call-stack analysis and O(1) space iterative optimization.

### How to Compile & Run:
```bash
cd Week1_Data_Structures_and_Algorithms
javac *.java
java InventoryManagement
```

---

## 🛢 3. PL/SQL Programming
**Directory:** [`Week1_PLSQL`](./Week1_PLSQL)

Comprehensive database scripts comprising schema design, sample data, and scenarios:

*   `schema.sql`: Full DDL structure including Audit and Error logs.
*   `sample_data.sql`: Seed data for boundary testing.
*   `Exercise1_ControlStructures.sql`: Cursor-driven discounts for seniors, VIP promotions, and loan due date alerts.
*   `Exercise2_ErrorHandling.sql`: Procedures with exception blocks (`SafeTransferFunds`, `UpdateSalary`, `AddNewCustomer`) and automated error logging.
*   `Exercise3_StoredProcedures.sql`: Stored procedures for monthly interest calculation, bonuses, and transfer transactions.
*   `Exercise4_Functions.sql`: Dynamic functions calculating age, monthly loan installments, and balance verifications.
*   `Exercise5_Triggers.sql`: Row triggers for audit logging, negative transaction blocks, and updatedAt updates.
*   `Exercise6_Cursors.sql`: Explicit cursors generating statements, applying fees, and adjusting loan rates.
*   `Exercise7_Packages.sql`: Modular packages (`CustomerManagement`, `EmployeeManagement`, `AccountOperations`).

---

## 🧪 4. JUnit & Mockito Testing
**Directory:** [`Week1_JUnit_Testing`](./Week1_JUnit_Testing)

A Maven-based Spring Boot test project verifying 68 unit, integration, and mock tests:

*   **JUnit Basic:** Setup, Assertions (`assertEquals`, `assertTrue`, `assertNull`), and Arrange-Act-Assert (AAA) pattern test fixtures (`@BeforeEach`, `@AfterEach`).
*   **JUnit Advanced:** `@ParameterizedTest` with `@ValueSource`/`@CsvSource`, exception assertions (`assertThrows`), execution orders (`@Order`), and Timeout assertions (`assertTimeout`).
*   **Mockito:** Mocking and stubbing, interaction verifications, argument matching, handling void methods, mock multiple returns, and verification order.
*   **Advanced Mockito:** Mocking repository databases, REST API clients, file IO operations, and network clients.
*   **Spring Testing:** Custom repository queries (`@DataJpaTest` on H2 memory DB), Web layer testing (`@WebMvcTest` with MockMvc and `@MockBean`), and full lifecycle Integration Tests (`@SpringBootTest` with `@Transactional`).

### How to Run Tests:
Ensure you have Maven installed, then run:
```bash
cd Week1_JUnit_Testing
mvn clean test
```

---

## 📝 5. SLF4J & Logback Logging
**Directory:** [`Week1_SL4J_Logging`](./Week1_SL4J_Logging)

A Maven-based SLF4J logging application demonstrating log levels, formatting, and file exports:

*   `LoggingExample.java`: Outputting `TRACE`, `DEBUG`, `INFO`, `WARN`, and `ERROR` levels.
*   `ParameterizedLoggingExample.java`: Efficient string concatenation logging without overhead.
*   `logback.xml`: Custom Logback configuration redirecting logs to console and `app.log`.
*   `AppendersLoggingExample.java`: Triggering ConsoleAppender and FileAppender logs.

### How to Compile & Run:
```bash
cd Week1_SL4J_Logging
mvn clean compile
mvn exec:java -Dexec.mainClass="com.cognizant.logging.AppendersLoggingExample"
```
Check `app.log` generated in the project root to view the exported logs.

---
*Developed by Ishan Parmar for the Cognizant Digital Nurture Deepskilling Program.*
