package com.bidgely.library.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Utility class for generating unique identifiers.
 */
public class IdGenerator {

    private static final AtomicLong counter = new AtomicLong(1000);

    private IdGenerator() {
        // Private constructor to prevent instantiation
    }

    /**
     * Generates a UUID-based unique identifier.
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generates a sequential numeric ID.
     */
    public static long generateSequentialId() {
        return counter.incrementAndGet();
    }

    /**
     * Generates a prefixed ID with a sequential number.
     */
    public static String generatePrefixedId(String prefix) {
        return prefix + "-" + generateSequentialId();
    }

    /**
     * Generates a book ID.
     */
    public static String generateBookId() {
        return generatePrefixedId("BOOK");
    }

    /**
     * Generates a member ID.
     */
    public static String generateMemberId() {
        return generatePrefixedId("MEM");
    }

    /**
     * Generates a loan ID.
     */
    public static String generateLoanId() {
        return generatePrefixedId("LOAN");
    }

    /**
     * Generates a reservation ID.
     */
    public static String generateReservationId() {
        return generatePrefixedId("RES");
    }

    /**
     * Generates an author ID.
     */
    public static String generateAuthorId() {
        return generatePrefixedId("AUTH");
    }

    /**
     * Generates a publisher ID.
     */
    public static String generatePublisherId() {
        return generatePrefixedId("PUB");
    }

    /**
     * Resets the counter (useful for testing).
     */
    public static void resetCounter() {
        counter.set(1000);
    }
}

