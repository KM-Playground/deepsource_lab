package com.bidgely.library.exception;

/**
 * Exception thrown when a book is not available for loan.
 */
public class BookNotAvailableException extends LibraryException {
    
    public BookNotAvailableException(String isbn) {
        super("Book not available for loan with ISBN: " + isbn);
    }
}

