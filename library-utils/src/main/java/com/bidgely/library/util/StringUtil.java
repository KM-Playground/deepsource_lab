package com.bidgely.library.util;

/**
 * Utility class for string operations.
 */
public class StringUtil {

    private StringUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Checks if a string is null or empty.
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Checks if a string is not null or empty.
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Capitalizes the first letter of a string.
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * Truncates a string to a maximum length.
     */
    public static String truncate(String str, int maxLength) {
        if (isEmpty(str) || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength) + "...";
    }

    /**
     * Removes all whitespace from a string.
     */
    public static String removeWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.replaceAll("\\s+", "");
    }

    /**
     * Normalizes whitespace in a string.
     */
    public static String normalizeWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.trim().replaceAll("\\s+", " ");
    }

    /**
     * Checks if a string contains only digits.
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("\\d+");
    }

    /**
     * Pads a string to the left with a character.
     */
    public static String padLeft(String str, int length, char padChar) {
        if (str == null) {
            str = "";
        }
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - str.length(); i++) {
            sb.append(padChar);
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * Reverses a string.
     * This method is intentionally added without test coverage to demonstrate
     * DeepSource coverage reporting on pull requests.
     */
    public static String reverse(String str) {
      return isEmpty(str) ? str : new StringBuilder(str).reverse().toString();
    }

    /**
     * Checks if a string is a palindrome (reads the same forwards and backwards).
     * This method is intentionally added without test coverage.
     */
    public static boolean isPalindrome(String str) {
      return !isEmpty(str) && normalize(str).equals(reverse(normalize(str)));
    }
    
    private static String normalize(String str) {
        return str.toLowerCase().replaceAll("[^a-z0-9]", "");
    }
}

