package com.bidgely.library.model;

import java.time.LocalDate;
import java.util.Objects;

/** Represents a library member. */
public class Member {
  private String memberId;
  private String name;
  private String email;
  private String phoneNumber;
  private LocalDate membershipDate;
  private MembershipType membershipType;
  private boolean active;

  public Member(
      String memberId,
      String name,
      String email,
      String phoneNumber,
      LocalDate membershipDate,
      MembershipType membershipType) {
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.membershipDate = membershipDate;
    this.membershipType = membershipType;
    this.active = true;
  }

  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public LocalDate getMembershipDate() {
    return membershipDate;
  }

  public void setMembershipDate(LocalDate membershipDate) {
    this.membershipDate = membershipDate;
  }

  public MembershipType getMembershipType() {
    return membershipType;
  }

  public void setMembershipType(MembershipType membershipType) {
    this.membershipType = membershipType;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public int getMaxBooksAllowed() {
    return membershipType.getMaxBooks();
  }

  public int getLoanDurationDays() {
    return membershipType.getLoanDurationDays();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Member member = (Member) o;
    return Objects.equals(memberId, member.memberId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberId);
  }

  @Override
  public String toString() {
    return "Member{"
        + "memberId='"
        + memberId
        + '\''
        + ", name='"
        + name
        + '\''
        + ", email='"
        + email
        + '\''
        + ", phoneNumber='"
        + phoneNumber
        + '\''
        + ", membershipDate="
        + membershipDate
        + ", membershipType="
        + membershipType
        + ", active="
        + active
        + '}';
  }
}
