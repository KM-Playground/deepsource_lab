package com.bidgely.library.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.bidgely.library.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

class ValidationUtilTest {

  @Test
  void testValidateNotEmpty_withValidString() throws InvalidInputException {
    ValidationUtil.validateNotEmpty("test", "Field");
    // No exception should be thrown
  }

  @Test
  void testValidateNotEmpty_withNull() {
    assertThatThrownBy(() -> ValidationUtil.validateNotEmpty(null, "Field"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("cannot be null or empty");
  }

  @Test
  void testValidateNotEmpty_withEmptyString() {
    assertThatThrownBy(() -> ValidationUtil.validateNotEmpty("", "Field"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("cannot be null or empty");
  }

  @Test
  void testValidateNotEmpty_withWhitespace() {
    assertThatThrownBy(() -> ValidationUtil.validateNotEmpty("   ", "Field"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("cannot be null or empty");
  }

  @Test
  void testValidateIsbn_withValidIsbn13() throws InvalidInputException {
    ValidationUtil.validateIsbn("9780134685991");
    // No exception should be thrown
  }

  @Test
  void testValidateIsbn_withValidIsbn10() throws InvalidInputException {
    ValidationUtil.validateIsbn("0134685997");
    // No exception should be thrown
  }

  @Test
  void testValidateIsbn_withInvalidIsbn() {
    assertThatThrownBy(() -> ValidationUtil.validateIsbn("invalid"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Invalid ISBN format");
  }

  @Test
  void testValidateEmail_withValidEmail() throws InvalidInputException {
    ValidationUtil.validateEmail("test@example.com");
    // No exception should be thrown
  }

  @Test
  void testValidateEmail_withInvalidEmail() {
    assertThatThrownBy(() -> ValidationUtil.validateEmail("invalid-email"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Invalid email format");
  }

  @Test
  void testValidateEmail_withNull() {
    assertThatThrownBy(() -> ValidationUtil.validateEmail(null))
        .isInstanceOf(InvalidInputException.class);
  }

  @Test
  void testValidatePhoneNumber_withValidPhone() throws InvalidInputException {
    ValidationUtil.validatePhoneNumber("1234567890");
    // No exception should be thrown
  }

  @Test
  void testValidatePhoneNumber_withInvalidPhone() {
    assertThatThrownBy(() -> ValidationUtil.validatePhoneNumber("123"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Invalid phone number format");
  }

  @Test
  void testValidatePhoneNumber_withLetters() {
    assertThatThrownBy(() -> ValidationUtil.validatePhoneNumber("12345abcde"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Invalid phone number format");
  }

  @Test
  void testValidatePositive_withPositiveNumber() throws InvalidInputException {
    ValidationUtil.validatePositive(5, "Field");
    // No exception should be thrown
  }

  @Test
  void testValidatePositive_withZero() {
    assertThatThrownBy(() -> ValidationUtil.validatePositive(0, "Field"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("must be positive");
  }

  @Test
  void testValidatePositive_withNegativeNumber() {
    assertThatThrownBy(() -> ValidationUtil.validatePositive(-5, "Field"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("must be positive");
  }

  @Test
  void testValidateNonNegative_withPositiveNumber() throws InvalidInputException {
    ValidationUtil.validateNonNegative(5, "Field");
    // No exception should be thrown
  }

  @Test
  void testValidateNonNegative_withZero() throws InvalidInputException {
    ValidationUtil.validateNonNegative(0, "Field");
    // No exception should be thrown
  }

  @Test
  void testValidateNonNegative_withNegativeNumber() {
    assertThatThrownBy(() -> ValidationUtil.validateNonNegative(-5, "Field"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("cannot be negative");
  }

  @Test
  void testValidateYear_withValidYear() throws InvalidInputException {
    ValidationUtil.validateYear(2023);
    // No exception should be thrown
  }

  @Test
  void testValidateYear_withTooOldYear() {
    assertThatThrownBy(() -> ValidationUtil.validateYear(999))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Invalid publication year");
  }

  @Test
  void testValidateYear_withFutureYear() {
    int futureYear = java.time.Year.now().getValue() + 2;
    assertThatThrownBy(() -> ValidationUtil.validateYear(futureYear))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Invalid publication year");
  }

  @Test
  void testValidateYear_withCurrentYear() throws InvalidInputException {
    int currentYear = java.time.Year.now().getValue();
    ValidationUtil.validateYear(currentYear);
    // No exception should be thrown
  }

  @Test
  void testValidateYear_withNextYear() throws InvalidInputException {
    int nextYear = java.time.Year.now().getValue() + 1;
    ValidationUtil.validateYear(nextYear);
    // No exception should be thrown
  }
}
