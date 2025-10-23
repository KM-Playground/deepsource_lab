package com.bidgely.library.service;

import com.bidgely.library.exception.*;
import com.bidgely.library.model.*;
import com.bidgely.library.util.ValidationUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for library operations.
 */
public class LibraryService {
    
    private final Map<String, Book> books;
    private final Map<String, Member> members;
    private final Map<String, Loan> loans;
    private final Map<String, List<Loan>> memberLoans;
    private static final double FINE_PER_DAY = 1.0;

    public LibraryService() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.loans = new HashMap<>();
        this.memberLoans = new HashMap<>();
    }

    // Book Management

    /**
     * Adds a new book to the library.
     */
    public void addBook(Book book) throws InvalidInputException {
        if (book == null) {
            throw new InvalidInputException("Book cannot be null");
        }
        ValidationUtil.validateIsbn(book.getIsbn());
        ValidationUtil.validateNotEmpty(book.getTitle(), "Title");
        ValidationUtil.validateNotEmpty(book.getAuthor(), "Author");
        ValidationUtil.validatePositive(book.getTotalCopies(), "Total copies");
        ValidationUtil.validateYear(book.getPublicationYear());
        
        books.put(book.getIsbn(), book);
    }

    /**
     * Finds a book by ISBN.
     */
    public Book findBookByIsbn(String isbn) throws BookNotFoundException {
        Book book = books.get(isbn);
        if (book == null) {
            throw new BookNotFoundException(isbn);
        }
        return book;
    }

    /**
     * Searches books by title.
     */
    public List<Book> searchBooksByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String searchTerm = title.toLowerCase();
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    /**
     * Searches books by author.
     */
    public List<Book> searchBooksByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String searchTerm = author.toLowerCase();
        return books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    /**
     * Gets all books in a category.
     */
    public List<Book> getBooksByCategory(BookCategory category) {
        if (category == null) {
            return new ArrayList<>();
        }
        return books.values().stream()
                .filter(book -> book.getCategory() == category)
                .collect(Collectors.toList());
    }

    /**
     * Gets all available books.
     */
    public List<Book> getAvailableBooks() {
        return books.values().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    /**
     * Updates book information.
     */
    public void updateBook(Book book) throws BookNotFoundException, InvalidInputException {
        if (book == null) {
            throw new InvalidInputException("Book cannot be null");
        }
        if (!books.containsKey(book.getIsbn())) {
            throw new BookNotFoundException(book.getIsbn());
        }
        ValidationUtil.validateNotEmpty(book.getTitle(), "Title");
        ValidationUtil.validateNotEmpty(book.getAuthor(), "Author");
        
        books.put(book.getIsbn(), book);
    }

    /**
     * Removes a book from the library.
     */
    public void removeBook(String isbn) throws BookNotFoundException {
        if (!books.containsKey(isbn)) {
            throw new BookNotFoundException(isbn);
        }
        books.remove(isbn);
    }

    // Member Management

    /**
     * Registers a new member.
     */
    public void registerMember(Member member) throws InvalidInputException {
        if (member == null) {
            throw new InvalidInputException("Member cannot be null");
        }
        ValidationUtil.validateNotEmpty(member.getMemberId(), "Member ID");
        ValidationUtil.validateNotEmpty(member.getName(), "Name");
        ValidationUtil.validateEmail(member.getEmail());
        ValidationUtil.validatePhoneNumber(member.getPhoneNumber());
        
        members.put(member.getMemberId(), member);
        memberLoans.put(member.getMemberId(), new ArrayList<>());
    }

    /**
     * Finds a member by ID.
     */
    public Member findMemberById(String memberId) throws MemberNotFoundException {
        Member member = members.get(memberId);
        if (member == null) {
            throw new MemberNotFoundException(memberId);
        }
        return member;
    }

    /**
     * Updates member information.
     */
    public void updateMember(Member member) throws MemberNotFoundException, InvalidInputException {
        if (member == null) {
            throw new InvalidInputException("Member cannot be null");
        }
        if (!members.containsKey(member.getMemberId())) {
            throw new MemberNotFoundException(member.getMemberId());
        }
        ValidationUtil.validateNotEmpty(member.getName(), "Name");
        ValidationUtil.validateEmail(member.getEmail());
        
        members.put(member.getMemberId(), member);
    }

    /**
     * Deactivates a member.
     */
    public void deactivateMember(String memberId) throws MemberNotFoundException {
        Member member = findMemberById(memberId);
        member.setActive(false);
    }

    /**
     * Gets all active members.
     */
    public List<Member> getActiveMembers() {
        return members.values().stream()
                .filter(Member::isActive)
                .collect(Collectors.toList());
    }

    // Loan Management

    /**
     * Issues a book loan to a member.
     */
    public Loan issueBook(String memberId, String isbn) throws LibraryException {
        Member member = findMemberById(memberId);
        Book book = findBookByIsbn(isbn);

        if (!member.isActive()) {
            throw new InvalidInputException("Member is not active");
        }

        if (!book.isAvailable()) {
            throw new BookNotAvailableException(isbn);
        }

        List<Loan> activeLoans = getActiveLoansByMember(memberId);
        if (activeLoans.size() >= member.getMaxBooksAllowed()) {
            throw new LoanLimitExceededException(memberId, member.getMaxBooksAllowed());
        }

        String loanId = generateLoanId();
        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanDate.plusDays(member.getLoanDurationDays());

        Loan loan = new Loan(loanId, memberId, isbn, loanDate, dueDate);
        loans.put(loanId, loan);
        memberLoans.get(memberId).add(loan);
        
        book.borrowCopy();

        return loan;
    }

    /**
     * Returns a book.
     */
    public void returnBook(String loanId) throws LibraryException {
        Loan loan = loans.get(loanId);
        if (loan == null) {
            throw new InvalidInputException("Loan not found with ID: " + loanId);
        }

        if (loan.getStatus() != LoanStatus.ACTIVE) {
            throw new InvalidInputException("Loan is not active");
        }

        Book book = findBookByIsbn(loan.getIsbn());
        book.returnCopy();

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        if (loan.isOverdue()) {
            double fine = calculateFine(loan);
            loan.setFineAmount(fine);
        }
    }

    /**
     * Calculates fine for overdue loan.
     */
    public double calculateFine(Loan loan) {
        if (loan == null || !loan.isOverdue()) {
            return 0.0;
        }
        return loan.getDaysOverdue() * FINE_PER_DAY;
    }

    /**
     * Gets all active loans for a member.
     */
    public List<Loan> getActiveLoansByMember(String memberId) {
        List<Loan> allLoans = memberLoans.get(memberId);
        if (allLoans == null) {
            return new ArrayList<>();
        }
        return allLoans.stream()
                .filter(loan -> loan.getStatus() == LoanStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    /**
     * Gets all overdue loans.
     */
    public List<Loan> getOverdueLoans() {
        return loans.values().stream()
                .filter(Loan::isOverdue)
                .collect(Collectors.toList());
    }

    /**
     * Gets loan history for a member.
     */
    public List<Loan> getLoanHistory(String memberId) {
        List<Loan> allLoans = memberLoans.get(memberId);
        if (allLoans == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(allLoans);
    }

    /**
     * Generates a unique loan ID.
     */
    private String generateLoanId() {
        return "LOAN-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }

    // Statistics

    /**
     * Gets total number of books.
     */
    public int getTotalBooks() {
        return books.size();
    }

    /**
     * Gets total number of members.
     */
    public int getTotalMembers() {
        return members.size();
    }

    /**
     * Gets total number of active loans.
     */
    public int getTotalActiveLoans() {
        return (int) loans.values().stream()
                .filter(loan -> loan.getStatus() == LoanStatus.ACTIVE)
                .count();
    }
}

