package com.bidgely.library.service;

import com.bidgely.library.exception.BookNotFoundException;
import com.bidgely.library.exception.InvalidInputException;
import com.bidgely.library.exception.MemberNotFoundException;
import com.bidgely.library.model.Book;
import com.bidgely.library.model.Member;
import com.bidgely.library.model.Reservation;
import com.bidgely.library.util.IdGenerator;
import com.bidgely.library.util.ValidationUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for managing book reservations.
 */
public class ReservationService {
    private final Map<String, Reservation> reservations;
    private final LibraryService libraryService;

    public ReservationService(LibraryService libraryService) {
        this.reservations = new HashMap<>();
        this.libraryService = libraryService;
    }

    /**
     * Creates a new reservation for a book.
     */
    public Reservation createReservation(String memberId, String isbn) {
        ValidationUtil.validateNotEmpty(memberId, "Member ID");
        ValidationUtil.validateIsbn(isbn);

        // Verify member exists
        Member member = libraryService.findMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member not found: " + memberId);
        }

        // Verify book exists
        Book book = libraryService.findBookByIsbn(isbn);
        if (book == null) {
            throw new BookNotFoundException("Book not found: " + isbn);
        }

        // Check if member already has an active reservation for this book
        List<Reservation> memberReservations = getActiveReservationsByMember(memberId);
        for (Reservation res : memberReservations) {
            if (res.getIsbn().equals(isbn)) {
                throw new InvalidInputException("Member already has an active reservation for this book");
            }
        }

        String reservationId = IdGenerator.generateReservationId();
        Reservation reservation = new Reservation(reservationId, memberId, isbn, LocalDate.now());
        reservations.put(reservationId, reservation);

        return reservation;
    }

    /**
     * Cancels a reservation.
     */
    public void cancelReservation(String reservationId) {
        ValidationUtil.validateNotEmpty(reservationId, "Reservation ID");

        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            throw new InvalidInputException("Reservation not found: " + reservationId);
        }

        reservation.cancel();
    }

    /**
     * Fulfills a reservation (when book is issued).
     */
    public void fulfillReservation(String reservationId) {
        ValidationUtil.validateNotEmpty(reservationId, "Reservation ID");

        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            throw new InvalidInputException("Reservation not found: " + reservationId);
        }

        reservation.fulfill();
    }

    /**
     * Gets all active reservations for a member.
     */
    public List<Reservation> getActiveReservationsByMember(String memberId) {
        return reservations.values().stream()
                .filter(r -> r.getMemberId().equals(memberId))
                .filter(r -> r.getStatus() == Reservation.ReservationStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    /**
     * Gets all active reservations for a book.
     */
    public List<Reservation> getActiveReservationsByBook(String isbn) {
        return reservations.values().stream()
                .filter(r -> r.getIsbn().equals(isbn))
                .filter(r -> r.getStatus() == Reservation.ReservationStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    /**
     * Gets all expired reservations.
     */
    public List<Reservation> getExpiredReservations() {
        return reservations.values().stream()
                .filter(Reservation::isExpired)
                .collect(Collectors.toList());
    }

    /**
     * Processes expired reservations by marking them as expired.
     */
    public int processExpiredReservations() {
        List<Reservation> expired = getExpiredReservations();
        for (Reservation reservation : expired) {
            reservation.setStatus(Reservation.ReservationStatus.EXPIRED);
        }
        return expired.size();
    }

    /**
     * Gets a reservation by ID.
     */
    public Reservation getReservation(String reservationId) {
        return reservations.get(reservationId);
    }

    /**
     * Gets all reservations.
     */
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }

    /**
     * Gets total number of reservations.
     */
    public int getTotalReservations() {
        return reservations.size();
    }
}

