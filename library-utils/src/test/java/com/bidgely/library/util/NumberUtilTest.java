package com.bidgely.library.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberUtilTest {

    @Test
    void testCheckEvenOrOdd_withEvenNumber() {
        assertThat(NumberUtil.checkEvenOrOdd(2)).isEqualTo("Even");
        assertThat(NumberUtil.checkEvenOrOdd(4)).isEqualTo("Even");
        assertThat(NumberUtil.checkEvenOrOdd(100)).isEqualTo("Even");
        assertThat(NumberUtil.checkEvenOrOdd(0)).isEqualTo("Even");
    }

    @Test
    void testGenerateHash_withValidInput() {
        // Only testing the happy path - not testing null or edge cases
        String hash = NumberUtil.generateHash("test");
        assertThat(hash).isNotNull();
        assertThat(hash).hasSize(32); // MD5 produces 32 character hex string
    }

    @Test
    void testCategorizeNumber_onlyPositiveNumbers() {
        // Only testing positive numbers - leaving negative numbers and zero uncovered
        assertThat(NumberUtil.categorizeNumber(5)).contains("Small positive");
        assertThat(NumberUtil.categorizeNumber(150)).contains("Medium-large positive");
        assertThat(NumberUtil.categorizeNumber(2000)).contains("Large positive");

        // Testing even/odd annotations
        assertThat(NumberUtil.categorizeNumber(10)).contains("(even)");
    }
}

