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
     * Validates and categorizes a password based on multiple security criteria.
     * Returns a category string indicating the password strength.
     *
     * @param password the password to validate
     * @return category string: "invalid", "weak", "moderate", "strong", or "very-strong"
     */
    public static String categorizePassword(String password) {
        if (password == null) {
            return "invalid";
        }

        int length = password.length();
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        boolean hasSpace = false;

        // Check character types
        for (int i = 0; i < length; i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isWhitespace(c)) {
                hasSpace = true;
            } else {
                hasSpecial = true;
            }
        }

        // Invalid if contains spaces
        if (hasSpace) {
            return "invalid";
        }

        // Invalid if too short
        if (length < 6) {
            return "invalid";
        }

        // Weak: only one character type
        if ((hasUpper && !hasLower && !hasDigit && !hasSpecial) ||
            (!hasUpper && hasLower && !hasDigit && !hasSpecial) ||
            (!hasUpper && !hasLower && hasDigit && !hasSpecial) ||
            (!hasUpper && !hasLower && !hasDigit && hasSpecial)) {
            return "weak";
        }

        // Moderate: two character types
        if ((hasUpper && hasLower && !hasDigit && !hasSpecial) ||
            (hasUpper && !hasLower && hasDigit && !hasSpecial) ||
            (hasUpper && !hasLower && !hasDigit && hasSpecial) ||
            (!hasUpper && hasLower && hasDigit && !hasSpecial) ||
            (!hasUpper && hasLower && !hasDigit && hasSpecial) ||
            (!hasUpper && !hasLower && hasDigit && hasSpecial)) {
            if (length >= 8) {
                return "moderate";
            } else {
                return "weak";
            }
        }

        // Strong: three character types
        if ((hasUpper && hasLower && hasDigit && !hasSpecial) ||
            (hasUpper && hasLower && !hasDigit && hasSpecial) ||
            (hasUpper && !hasLower && hasDigit && hasSpecial) ||
            (!hasUpper && hasLower && hasDigit && hasSpecial)) {
            if (length >= 10) {
                return "strong";
            } else if (length >= 8) {
                return "moderate";
            } else {
                return "weak";
            }
        }

        // Very strong: all four character types
        if (hasUpper && hasLower && hasDigit && hasSpecial) {
            if (length >= 12) {
                return "very-strong";
            } else if (length >= 10) {
                return "strong";
            } else if (length >= 8) {
                return "moderate";
            } else {
                return "weak";
            }
        }

        return "weak";
    }
}

