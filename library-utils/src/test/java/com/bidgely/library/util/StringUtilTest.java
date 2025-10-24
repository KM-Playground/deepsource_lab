package com.bidgely.library.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilTest {

    @Test
    void testIsEmpty_withNull() {
        assertThat(StringUtil.isEmpty(null)).isTrue();
    }

    @Test
    void testIsEmpty_withEmptyString() {
        assertThat(StringUtil.isEmpty("")).isTrue();
    }

    @Test
    void testIsEmpty_withWhitespace() {
        assertThat(StringUtil.isEmpty("   ")).isTrue();
    }

    @Test
    void testIsEmpty_withNonEmptyString() {
        assertThat(StringUtil.isEmpty("test")).isFalse();
    }

    @Test
    void testIsNotEmpty_withNull() {
        assertThat(StringUtil.isNotEmpty(null)).isFalse();
    }

    @Test
    void testIsNotEmpty_withEmptyString() {
        assertThat(StringUtil.isNotEmpty("")).isFalse();
    }

    @Test
    void testIsNotEmpty_withNonEmptyString() {
        assertThat(StringUtil.isNotEmpty("test")).isTrue();
    }

    @Test
    void testCapitalize_withLowerCase() {
        assertThat(StringUtil.capitalize("hello")).isEqualTo("Hello");
    }

    @Test
    void testCapitalize_withUpperCase() {
        assertThat(StringUtil.capitalize("HELLO")).isEqualTo("Hello");
    }

    @Test
    void testCapitalize_withMixedCase() {
        assertThat(StringUtil.capitalize("hELLO")).isEqualTo("Hello");
    }

    @Test
    void testCapitalize_withNull() {
        assertThat(StringUtil.capitalize(null)).isNull();
    }

    @Test
    void testCapitalize_withEmptyString() {
        assertThat(StringUtil.capitalize("")).isEmpty();
    }

    @Test
    void testTruncate_withShortString() {
        assertThat(StringUtil.truncate("hello", 10)).isEqualTo("hello");
    }

    @Test
    void testTruncate_withLongString() {
        assertThat(StringUtil.truncate("hello world", 5)).isEqualTo("hello...");
    }

    @Test
    void testTruncate_withExactLength() {
        assertThat(StringUtil.truncate("hello", 5)).isEqualTo("hello");
    }

    @Test
    void testTruncate_withNull() {
        assertThat(StringUtil.truncate(null, 5)).isNull();
    }

    @Test
    void testRemoveWhitespace() {
        assertThat(StringUtil.removeWhitespace("hello world")).isEqualTo("helloworld");
    }

    @Test
    void testRemoveWhitespace_withMultipleSpaces() {
        assertThat(StringUtil.removeWhitespace("hello   world")).isEqualTo("helloworld");
    }

    @Test
    void testRemoveWhitespace_withTabs() {
        assertThat(StringUtil.removeWhitespace("hello\tworld")).isEqualTo("helloworld");
    }

    @Test
    void testRemoveWhitespace_withNull() {
        assertThat(StringUtil.removeWhitespace(null)).isNull();
    }

    @Test
    void testNormalizeWhitespace() {
        assertThat(StringUtil.normalizeWhitespace("hello   world")).isEqualTo("hello world");
    }

    @Test
    void testNormalizeWhitespace_withLeadingTrailing() {
        assertThat(StringUtil.normalizeWhitespace("  hello world  ")).isEqualTo("hello world");
    }

    @Test
    void testNormalizeWhitespace_withNull() {
        assertThat(StringUtil.normalizeWhitespace(null)).isNull();
    }

    @Test
    void testIsNumeric_withDigits() {
        assertThat(StringUtil.isNumeric("12345")).isTrue();
    }

    @Test
    void testIsNumeric_withLetters() {
        assertThat(StringUtil.isNumeric("abc")).isFalse();
    }

    @Test
    void testIsNumeric_withMixed() {
        assertThat(StringUtil.isNumeric("123abc")).isFalse();
    }

    @Test
    void testIsNumeric_withNull() {
        assertThat(StringUtil.isNumeric(null)).isFalse();
    }

    @Test
    void testIsNumeric_withEmptyString() {
        assertThat(StringUtil.isNumeric("")).isFalse();
    }

    @Test
    void testPadLeft_withShortString() {
        assertThat(StringUtil.padLeft("123", 5, '0')).isEqualTo("00123");
    }

    @Test
    void testPadLeft_withLongString() {
        assertThat(StringUtil.padLeft("12345", 3, '0')).isEqualTo("12345");
    }

    @Test
    void testPadLeft_withExactLength() {
        assertThat(StringUtil.padLeft("123", 3, '0')).isEqualTo("123");
    }

    @Test
    void testPadLeft_withNull() {
        assertThat(StringUtil.padLeft(null, 5, '0')).isEqualTo("00000");
    }

    @Test
    void testCategorizePassword_withNull() {
        assertThat(StringUtil.categorizePassword(null)).isEqualTo("invalid");
    }

    @Test
    void testCategorizePassword_withSpaces() {
        assertThat(StringUtil.categorizePassword("pass word")).isEqualTo("invalid");
    }

    @Test
    void testCategorizePassword_tooShort() {
        assertThat(StringUtil.categorizePassword("abc")).isEqualTo("invalid");
    }

    @Test
    void testCategorizePassword_weakOnlyLower() {
        assertThat(StringUtil.categorizePassword("abcdef")).isEqualTo("weak");
    }

    @Test
    void testCategorizePassword_weakOnlyUpper() {
        assertThat(StringUtil.categorizePassword("ABCDEF")).isEqualTo("weak");
    }

    @Test
    void testCategorizePassword_weakOnlyDigits() {
        assertThat(StringUtil.categorizePassword("123456")).isEqualTo("weak");
    }

    @Test
    void testCategorizePassword_weakOnlySpecial() {
        assertThat(StringUtil.categorizePassword("!@#$%^")).isEqualTo("weak");
    }

    @Test
    void testCategorizePassword_weakTwoTypesShort() {
        assertThat(StringUtil.categorizePassword("Abc123")).isEqualTo("weak");
    }

    @Test
    void testCategorizePassword_moderateTwoTypes() {
        assertThat(StringUtil.categorizePassword("Abcdefgh")).isEqualTo("moderate");
    }

    @Test
    void testCategorizePassword_moderateThreeTypesShort() {
        assertThat(StringUtil.categorizePassword("Abc123!@")).isEqualTo("moderate");
    }

    @Test
    void testCategorizePassword_strongThreeTypes() {
        assertThat(StringUtil.categorizePassword("Abcdef1234")).isEqualTo("strong");
    }

    @Test
    void testCategorizePassword_strongFourTypesShort() {
        assertThat(StringUtil.categorizePassword("Abcd123!@#")).isEqualTo("strong");
    }

    @Test
    void testCategorizePassword_veryStrong() {
        assertThat(StringUtil.categorizePassword("Abcdef123!@#")).isEqualTo("very-strong");
    }
}

