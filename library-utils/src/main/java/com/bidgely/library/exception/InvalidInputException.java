package com.bidgely.library.exception;

/** Exception thrown when invalid input is provided. */
public class InvalidInputException extends LibraryException {

  public InvalidInputException(String message) {
    super(message);
  }
}
