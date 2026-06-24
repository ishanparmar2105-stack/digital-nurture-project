package com.cognizant.advanced;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import com.cognizant.basic.AssertionsTest;
import com.cognizant.basic.CalculatorTest;

@Suite
@SelectClasses({
    CalculatorTest.class,
    AssertionsTest.class,
    EvenCheckerTest.class,
    ExceptionThrowerTest.class,
    PerformanceTesterTest.class,
    OrderedTests.class
})
public class AllTests {
    // This class remains empty. It only serves as a holder for the above annotations.
}
