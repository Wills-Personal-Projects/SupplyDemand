package com.SupplyDemand.Tests;

import com.SupplyDemand.SupplyDemand;
// import SupplyDemand.jh61b.grader.GradedTest;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CSE 460 Software Analysis and Design Project - Sample Test Cases
 * This class contains some test cases that will be used in automated grading of your project.
 * Note that these test cases are not exhaustive - other cases and abnormal inputs will be used as well.
 *
 * These tests concern only with the correctness and robustness of your implementation. You are still required to
 * adopt Publisher-Subscriber pattern in your design and make use of good programming practises. Otherwise you can
 * suffer heavy penalty for a 100% correct implementation!
 *
 * The undisclosed part of the test will differ from this test somewhat:
 * - Test cases are automatically generated with random characters (other than commas and leading/trailing spaces).
 * - The generated test cases can be very long and contains unusual sequences.
 * - Instead of testing the output of your program against a "standard answer", your output would be compared against
 *   the output of a "Reference Implementation" (RI), which is seen as the authoritative answer.
 *   - Could the RI be wrong? Yes, but unlikely. Contact us if you believe it is the case.
 *
 * @author Xuanli Lin <kazumi@asu.edu>
 * @version 1.0
 */
public class PublicTestCases {
    private static SupplyDemand sd;

    // Create SupplyDemand object to test with
    @BeforeClass
    public static void setupSupplyDemand() {
        sd = new SupplyDemand();
    }

    // Reset the sd object every time a test finishes so that it can accept a new batch of commands
    @After
    public void resetSupplyDemand() {
        sd.reset();
    }

    // Note: @GradedTest is for GradeScope scoring only and does not affect the test in any way.
    @Test
    // @GradedTest(name = "Normal 1 - Subscribes and publishes on random order", max_score = 15)
    public void testSubPub() {
        // Expected output
        List<String> expected = new ArrayList<>(Arrays.asList(
                "target notified of dole brand banana from dole",
                "target notified of mott's brand applesauce from keurig drpepper",
                "target notified of chiquita brand banana from chiquita"));

        // Feed the SD object with some commands
        sd.processInput("subscribe, Target, banana");
        sd.processInput("publish, Dole, banana, Dole");
        sd.processInput("subscribe, Target, applesauce");
        sd.processInput("publish, Keurig DrPepper, applesauce, Mott's");
        sd.processInput("publish, Chiquita, banana, Chiquita");

        // Obtain the actual result from your SD object and compare it with the expected output
        // Note that each entry is stripped and converted to lowercase before comparison
        List<String> actual = sd.getAggregatedOutput().stream()
                .map(String::toLowerCase)
                .map(String::strip)
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    // @GradedTest(name = "Normal 2 - Multi retailers subscribe to & unsub from the same type", max_score = 15)
    public void testMultiSubUnsub() {
        // Expected output
        List<String> expected = new ArrayList<>(Arrays.asList(
                "target notified of dole brand banana from dole",
                "walmart notified of dole brand banana from dole",
                "target notified of pole brand banana from dole",
                "walmart notified of mole brand banana from dole",
                "target notified of mole brand banana from dole"));

        // Create SupplyDemand object to test with
        sd.processInput("subscribe, Target, banana");
        sd.processInput("subscribe, Walmart, banana");
        sd.processInput("publish, Dole, banana, Dole");
        sd.processInput("unsubscribe, Walmart, banana");
        sd.processInput("publish, Dole, banana, Pole");
        sd.processInput("unsubscribe, Target, banana");
        sd.processInput("publish, Dole, banana, Bole");
        sd.processInput("subscribe, Walmart, banana");
        sd.processInput("subscribe, Target, banana");
        sd.processInput("publish, Dole, banana, Mole");

        // Obtain the actual result from your SD object and compare it with the expected output
        // Note that each entry is stripped and converted to lowercase before comparison
        List<String> actual = sd.getAggregatedOutput().stream()
                .map(String::toLowerCase)
                .map(String::strip)
                .collect(Collectors.toList());
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    // @GradedTest(name = "Abnormal 1 - Illegal parameter lengths", max_score = 10)
    public void testIllegalParamLens() {
        // Expected output (nothing)
        List<String> expected = new ArrayList<>();

        sd.processInput("subscribe, Target, banana, 04082020");
        sd.processInput("publish, Dole, banana, Dole");
        sd.processInput("subscribe, Target, banana");
        sd.processInput("publish, Dole, banana, Dole, $150.00");

        // Obtain the actual result from your SD object and compare it with the expected output
        List<String> actual = sd.getAggregatedOutput();

        assertEquals(expected, actual);
    }
}
