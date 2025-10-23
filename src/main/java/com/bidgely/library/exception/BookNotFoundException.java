package com.bidgely.library.exception;

/**
 * Exception thrown when a book is not found.
 */
public class BookNotFoundException extends LibraryException {
    
    public BookNotFoundException(String isbn) {
        super("Book not found with ISBN: " + isbn);
    }
}

