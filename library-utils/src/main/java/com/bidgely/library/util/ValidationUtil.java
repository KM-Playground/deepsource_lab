package com.bidgely.library.util;

import com.bidgely.library.exception.InvalidInputException;

/**
 * Utility class for input validation.
 */
public class ValidationUtil {

    private static final String ISBN_PATTERN = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PHONE_PATTERN = "^[0-9]{10}$";

    private ValidationUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Validates if a string is not null or empty.
     */
    public static void validateNotEmpty(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be null or empty");
        }
    }

    /**
     * Validates ISBN format.
     */
    public static void validateIsbn(String isbn) throws InvalidInputException {
        validateNotEmpty(isbn, "ISBN");
        if (!isbn.matches(ISBN_PATTERN)) {
            throw new InvalidInputException("Invalid ISBN format: " + isbn);
        }
    }

    /**
     * Validates email format.
     */
    public static void validateEmail(String email) throws InvalidInputException {
        validateNotEmpty(email, "Email");
        if (!email.matches(EMAIL_PATTERN)) {
            throw new InvalidInputException("Invalid email format: " + email);
        }
    }

    /**
     * Validates phone number format.
     */
    public static void validatePhoneNumber(String phoneNumber) throws InvalidInputException {
        validateNotEmpty(phoneNumber, "Phone number");
        if (!phoneNumber.matches(PHONE_PATTERN)) {
            throw new InvalidInputException("Invalid phone number format: " + phoneNumber);
        }
    }

    /**
     * Validates that a number is positive.
     */
    public static void validatePositive(int value, String fieldName) throws InvalidInputException {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be positive");
        }
    }

    /**
     * Validates that a number is non-negative.
     */
    public static void validateNonNegative(int value, String fieldName) throws InvalidInputException {
        if (value < 0) {
            throw new InvalidInputException(fieldName + " cannot be negative");
        }
    }

    /**
     * Validates year is reasonable.
     */
    public static void validateYear(int year) throws InvalidInputException {
        int currentYear = java.time.Year.now().getValue();
        if (year < 1000 || year > currentYear + 1) {
            throw new InvalidInputException("Invalid publication year: " + year);
        }
    }
}

