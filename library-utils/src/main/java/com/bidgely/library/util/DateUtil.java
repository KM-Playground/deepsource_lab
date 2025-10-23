package com.bidgely.library.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/** Utility class for date operations. */
public class DateUtil {

  private static final DateTimeFormatter DEFAULT_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private DateUtil() {
    // Private constructor to prevent instantiation
  }

  /** Formats a date to string. */
  public static String formatDate(LocalDate date) {
    if (date == null) {
      return null;
    }
    return date.format(DEFAULT_FORMATTER);
  }

  /** Parses a string to date. */
  public static LocalDate parseDate(String dateStr) {
    if (dateStr == null || dateStr.trim().isEmpty()) {
      return null;
    }
    return LocalDate.parse(dateStr, DEFAULT_FORMATTER);
  }

  /** Calculates days between two dates. */
  public static long daysBetween(LocalDate start, LocalDate end) {
    if (start == null || end == null) {
      return 0;
    }
    return ChronoUnit.DAYS.between(start, end);
  }

  /** Adds days to a date. */
  public static LocalDate addDays(LocalDate date, int days) {
    if (date == null) {
      return null;
    }
    return date.plusDays(days);
  }

  /** Checks if a date is in the past. */
  public static boolean isPast(LocalDate date) {
    if (date == null) {
      return false;
    }
    return date.isBefore(LocalDate.now());
  }

  /** Checks if a date is in the future. */
  public static boolean isFuture(LocalDate date) {
    if (date == null) {
      return false;
    }
    return date.isAfter(LocalDate.now());
  }

  /** Gets current date. */
  public static LocalDate today() {
    return LocalDate.now();
  }
}
