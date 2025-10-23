package com.bidgely.library.model;

/** Types of library memberships with different privileges. */
public enum MembershipType {
  BASIC(3, 14),
  PREMIUM(5, 21),
  STUDENT(4, 14),
  FACULTY(10, 30);

  private final int maxBooks;
  private final int loanDurationDays;

  MembershipType(int maxBooks, int loanDurationDays) {
    this.maxBooks = maxBooks;
    this.loanDurationDays = loanDurationDays;
  }

  public int getMaxBooks() {
    return maxBooks;
  }

  public int getLoanDurationDays() {
    return loanDurationDays;
  }
}
