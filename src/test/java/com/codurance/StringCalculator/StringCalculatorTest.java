package com.codurance.StringCalculator;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * A test class for the StringCalculator class.
 */
public class StringCalculatorTest {

    @Test
    public void testAdd_ExceptionThrown() {
        StringCalculator calculator = new StringCalculator();

        // Step 1: Simple Calculator
        assertEquals(0, calculator.Add(""));
        assertEquals(4, calculator.Add(("4")));
        assertEquals(3, calculator.Add("1,2"));

        // Step 2: Arbitrary number size
        assertEquals(45, calculator.Add("1,2,3,4,5,6,7,8,9"));

        // Step 3: Newline separator
        assertEquals(6, calculator.Add("1\n2,3"));

        // Step 4: Custom separators
        assertEquals(3, calculator.Add("//;\n1;2"));
        assertEquals(10, calculator.Add("//;\n10"));
        assertEquals(801, calculator.Add("//;\n12,3456;789"));

        // Step 5: Disallow negatives
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.Add("1,-2,-3");
        });
        String expectedMessage = "negatives not allowed: -2";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        // Step 6: Ignore numbers bigger than 1000
        assertEquals(2, calculator.Add("1001,2"));

        // Step 7: Arbitrary-length separators
        assertEquals(6, calculator.Add("//[***]\n1***2***3"));

        // Step 8: Multiple single-length separators
        assertEquals(6, calculator.Add("//[*][%]\n1*2%3"));

        // Step 9: Multiple longer-length separators
        assertEquals(6, calculator.Add("//[foo][bar]\n1foo2bar3"));
    }
}
