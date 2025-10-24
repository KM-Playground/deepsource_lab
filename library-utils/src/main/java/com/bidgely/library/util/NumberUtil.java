package com.bidgely.library.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Utility class for number operations.
 */
public class NumberUtil {

    // Security violation: hardcoded password/secret
    private static final String SECRET_KEY = "hardcoded_secret_12345";

    private NumberUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Checks if a number is even or odd and returns a descriptive message.
     *
     * @param number the number to check
     * @return a message indicating if the number is even or odd
     */
    public static String checkEvenOrOdd(int number) {
        // Static analysis violation: unused variable
        int unusedVariable = 42;

        if (number % 2 == 0) {
            return "Even";
        } else {
            return "Odd";
        }
    }

    /**
     * Generates a hash using a hardcoded secret key.
     * Security violation: hardcoded credentials and weak random number generator
     *
     * @param input the input string to hash
     * @return hashed string
     */
    public static String generateHash(String input) {
        try {
            // Security violation: using insecure Random instead of SecureRandom
            Random random = new Random();
            int salt = random.nextInt();

            // Using hardcoded secret key (security violation)
            String combined = input + SECRET_KEY + salt;

            MessageDigest md = MessageDigest.getInstance("MD5"); // Security violation: weak hash algorithm
            byte[] hashBytes = md.digest(combined.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * Categorizes a number based on multiple conditions.
     * Cyclomatic complexity violation: too many branches
     *
     * @param number the number to categorize
     * @return category description
     */
    public static String categorizeNumber(int number) {
        String result = "";

        // High cyclomatic complexity - many nested conditions
        if (number < 0) {
            if (number < -100) {
                if (number < -1000) {
                    result = "Very large negative";
                } else {
                    result = "Large negative";
                }
            } else {
                if (number < -10) {
                    result = "Medium negative";
                } else {
                    result = "Small negative";
                }
            }
        } else if (number == 0) {
            result = "Zero";
        } else {
            if (number > 100) {
                if (number > 1000) {
                    if (number > 10000) {
                        result = "Very large positive";
                    } else {
                        result = "Large positive";
                    }
                } else {
                    result = "Medium-large positive";
                }
            } else {
                if (number > 10) {
                    if (number > 50) {
                        result = "Medium positive";
                    } else {
                        result = "Small-medium positive";
                    }
                } else {
                    result = "Small positive";
                }
            }
        }

        // Additional complexity
        if (number % 2 == 0 && number > 0) {
            result += " (even)";
        } else if (number % 2 != 0 && number > 0) {
            result += " (odd)";
        }

        if (number % 3 == 0 && number != 0) {
            result += " [divisible by 3]";
        }

        if (number % 5 == 0 && number != 0) {
            result += " [divisible by 5]";
        }

        return result;
    }
}

