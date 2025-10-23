package com.bidgely.library.model;

import java.time.LocalDate;
import java.util.Objects;

/** Represents a book reservation by a member. */
public class Reservation {
  private String reservationId;
  private String memberId;
  private String isbn;
  private LocalDate reservationDate;
  private LocalDate expiryDate;
  private ReservationStatus status;

  public Reservation(
      String reservationId, String memberId, String isbn, LocalDate reservationDate) {
    this.reservationId = reservationId;
    this.memberId = memberId;
    this.isbn = isbn;
    this.reservationDate = reservationDate;
    this.expiryDate = reservationDate.plusDays(7); // Reservation valid for 7 days
    this.status = ReservationStatus.ACTIVE;
  }

  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public LocalDate getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(LocalDate reservationDate) {
    this.reservationDate = reservationDate;
  }

  public LocalDate getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(LocalDate expiryDate) {
    this.expiryDate = expiryDate;
  }

  public ReservationStatus getStatus() {
    return status;
  }

  public void setStatus(ReservationStatus status) {
    this.status = status;
  }

  public boolean isExpired() {
    return LocalDate.now().isAfter(expiryDate) && status == ReservationStatus.ACTIVE;
  }

  public void cancel() {
    this.status = ReservationStatus.CANCELLED;
  }

  public void fulfill() {
    this.status = ReservationStatus.FULFILLED;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Reservation that = (Reservation) o;
    return Objects.equals(reservationId, that.reservationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reservationId);
  }

  @Override
  public String toString() {
    return "Reservation{"
        + "reservationId='"
        + reservationId
        + '\''
        + ", memberId='"
        + memberId
        + '\''
        + ", isbn='"
        + isbn
        + '\''
        + ", status="
        + status
        + ", expiryDate="
        + expiryDate
        + '}';
  }

  public enum ReservationStatus {
    ACTIVE,
    FULFILLED,
    CANCELLED,
    EXPIRED
  }
}
