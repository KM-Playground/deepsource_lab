package com.bidgely.library.exception;

/**
 * Exception thrown when a member is not found.
 */
public class MemberNotFoundException extends LibraryException {
    
    public MemberNotFoundException(String memberId) {
        super("Member not found with ID: " + memberId);
    }
}

