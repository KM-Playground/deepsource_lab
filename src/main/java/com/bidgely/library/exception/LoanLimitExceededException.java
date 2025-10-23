package com.bidgely.library.exception;

/**
 * Exception thrown when a member exceeds their loan limit.
 */
public class LoanLimitExceededException extends LibraryException {
    
    public LoanLimitExceededException(String memberId, int limit) {
        super("Member " + memberId + " has exceeded loan limit of " + limit + " books");
    }
}

