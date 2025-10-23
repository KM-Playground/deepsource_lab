package com.bidgely.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.bidgely.library.exception.*;
import com.bidgely.library.model.*;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryServiceTest {

  private LibraryService libraryService;
  private Book testBook;
  private Member testMember;

  @BeforeEach
  void setUp() throws Exception {
    libraryService = new LibraryService();

    testBook =
        new Book(
            "9780134685991",
            "Effective Java",
            "Joshua Bloch",
            "Addison-Wesley",
            2018,
            5,
            BookCategory.TECHNOLOGY);

    testMember =
        new Member(
            "M001",
            "John Doe",
            "john.doe@example.com",
            "1234567890",
            LocalDate.of(2023, 1, 1),
            MembershipType.PREMIUM);

    libraryService.addBook(testBook);
    libraryService.registerMember(testMember);
  }

  // Book Management Tests

  @Test
  void testAddBook_success() throws Exception {
    Book newBook =
        new Book(
            "9780132350884",
            "Clean Code",
            "Robert Martin",
            "Prentice Hall",
            2008,
            3,
            BookCategory.TECHNOLOGY);
    libraryService.addBook(newBook);

    Book found = libraryService.findBookByIsbn("9780132350884");
    assertThat(found).isNotNull();
    assertThat(found.getTitle()).isEqualTo("Clean Code");
  }

  @Test
  void testAddBook_withNullBook() {
    assertThatThrownBy(() -> libraryService.addBook(null))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Book cannot be null");
  }

  @Test
  void testAddBook_withInvalidIsbn() {
    Book invalidBook =
        new Book("invalid", "Test", "Author", "Publisher", 2020, 1, BookCategory.FICTION);
    assertThatThrownBy(() -> libraryService.addBook(invalidBook))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Invalid ISBN format");
  }

  @Test
  void testAddBook_withEmptyTitle() {
    Book invalidBook =
        new Book("9780134685991", "", "Author", "Publisher", 2020, 1, BookCategory.FICTION);
    assertThatThrownBy(() -> libraryService.addBook(invalidBook))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Title");
  }

  @Test
  void testAddBook_withInvalidYear() {
    Book invalidBook =
        new Book("9780134685991", "Test", "Author", "Publisher", 999, 1, BookCategory.FICTION);
    assertThatThrownBy(() -> libraryService.addBook(invalidBook))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Invalid publication year");
  }

  @Test
  void testFindBookByIsbn_success() throws Exception {
    Book found = libraryService.findBookByIsbn("9780134685991");
    assertThat(found).isNotNull();
    assertThat(found.getTitle()).isEqualTo("Effective Java");
  }

  @Test
  void testFindBookByIsbn_notFound() {
    assertThatThrownBy(() -> libraryService.findBookByIsbn("9999999999999"))
        .isInstanceOf(BookNotFoundException.class);
  }

  @Test
  void testSearchBooksByTitle() {
    List<Book> results = libraryService.searchBooksByTitle("Effective");
    assertThat(results).hasSize(1);
    assertThat(results.get(0).getTitle()).isEqualTo("Effective Java");
  }

  @Test
  void testSearchBooksByTitle_caseInsensitive() {
    List<Book> results = libraryService.searchBooksByTitle("effective");
    assertThat(results).hasSize(1);
  }

  @Test
  void testSearchBooksByTitle_noResults() {
    List<Book> results = libraryService.searchBooksByTitle("Nonexistent");
    assertThat(results).isEmpty();
  }

  @Test
  void testSearchBooksByAuthor() {
    List<Book> results = libraryService.searchBooksByAuthor("Bloch");
    assertThat(results).hasSize(1);
    assertThat(results.get(0).getAuthor()).isEqualTo("Joshua Bloch");
  }

  @Test
  void testGetBooksByCategory() {
    List<Book> results = libraryService.getBooksByCategory(BookCategory.TECHNOLOGY);
    assertThat(results).hasSize(1);
  }

  @Test
  void testGetAvailableBooks() {
    List<Book> results = libraryService.getAvailableBooks();
    assertThat(results).hasSize(1);
  }

  @Test
  void testUpdateBook_success() throws Exception {
    testBook.setTitle("Effective Java 3rd Edition");
    libraryService.updateBook(testBook);

    Book updated = libraryService.findBookByIsbn("9780134685991");
    assertThat(updated.getTitle()).isEqualTo("Effective Java 3rd Edition");
  }

  @Test
  void testUpdateBook_notFound() {
    Book newBook =
        new Book("9999999999999", "Test", "Author", "Publisher", 2020, 1, BookCategory.FICTION);
    assertThatThrownBy(() -> libraryService.updateBook(newBook))
        .isInstanceOf(BookNotFoundException.class);
  }

  @Test
  void testRemoveBook_success() throws Exception {
    libraryService.removeBook("9780134685991");
    assertThatThrownBy(() -> libraryService.findBookByIsbn("9780134685991"))
        .isInstanceOf(BookNotFoundException.class);
  }

  @Test
  void testRemoveBook_notFound() {
    assertThatThrownBy(() -> libraryService.removeBook("9999999999999"))
        .isInstanceOf(BookNotFoundException.class);
  }

  // Member Management Tests

  @Test
  void testRegisterMember_success() throws Exception {
    Member newMember =
        new Member(
            "M002",
            "Jane Doe",
            "jane.doe@example.com",
            "9876543210",
            LocalDate.now(),
            MembershipType.BASIC);
    libraryService.registerMember(newMember);

    Member found = libraryService.findMemberById("M002");
    assertThat(found).isNotNull();
    assertThat(found.getName()).isEqualTo("Jane Doe");
  }

  @Test
  void testRegisterMember_withNullMember() {
    assertThatThrownBy(() -> libraryService.registerMember(null))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Member cannot be null");
  }

  @Test
  void testRegisterMember_withInvalidEmail() {
    Member invalidMember =
        new Member(
            "M002",
            "Jane Doe",
            "invalid-email",
            "9876543210",
            LocalDate.now(),
            MembershipType.BASIC);
    assertThatThrownBy(() -> libraryService.registerMember(invalidMember))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Invalid email format");
  }

  @Test
  void testRegisterMember_withInvalidPhone() {
    Member invalidMember =
        new Member(
            "M002", "Jane Doe", "jane@example.com", "123", LocalDate.now(), MembershipType.BASIC);
    assertThatThrownBy(() -> libraryService.registerMember(invalidMember))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Invalid phone number format");
  }

  @Test
  void testFindMemberById_success() throws Exception {
    Member found = libraryService.findMemberById("M001");
    assertThat(found).isNotNull();
    assertThat(found.getName()).isEqualTo("John Doe");
  }

  @Test
  void testFindMemberById_notFound() {
    assertThatThrownBy(() -> libraryService.findMemberById("M999"))
        .isInstanceOf(MemberNotFoundException.class);
  }

  @Test
  void testUpdateMember_success() throws Exception {
    testMember.setName("John Smith");
    libraryService.updateMember(testMember);

    Member updated = libraryService.findMemberById("M001");
    assertThat(updated.getName()).isEqualTo("John Smith");
  }

  @Test
  void testDeactivateMember_success() throws Exception {
    libraryService.deactivateMember("M001");

    Member member = libraryService.findMemberById("M001");
    assertThat(member.isActive()).isFalse();
  }

  @Test
  void testGetActiveMembers() {
    List<Member> activeMembers = libraryService.getActiveMembers();
    assertThat(activeMembers).hasSize(1);
  }

  // Loan Management Tests

  @Test
  void testIssueBook_success() throws Exception {
    Loan loan = libraryService.issueBook("M001", "9780134685991");

    assertThat(loan).isNotNull();
    assertThat(loan.getMemberId()).isEqualTo("M001");
    assertThat(loan.getIsbn()).isEqualTo("9780134685991");
    assertThat(loan.getStatus()).isEqualTo(LoanStatus.ACTIVE);

    Book book = libraryService.findBookByIsbn("9780134685991");
    assertThat(book.getAvailableCopies()).isEqualTo(4);
  }

  @Test
  void testIssueBook_memberNotFound() {
    assertThatThrownBy(() -> libraryService.issueBook("M999", "9780134685991"))
        .isInstanceOf(MemberNotFoundException.class);
  }

  @Test
  void testIssueBook_bookNotFound() {
    assertThatThrownBy(() -> libraryService.issueBook("M001", "9999999999999"))
        .isInstanceOf(BookNotFoundException.class);
  }

  @Test
  void testIssueBook_bookNotAvailable() throws Exception {
    testBook.setAvailableCopies(0);

    assertThatThrownBy(() -> libraryService.issueBook("M001", "9780134685991"))
        .isInstanceOf(BookNotAvailableException.class);
  }

  @Test
  void testIssueBook_memberInactive() throws Exception {
    testMember.setActive(false);

    assertThatThrownBy(() -> libraryService.issueBook("M001", "9780134685991"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("not active");
  }

  @Test
  void testIssueBook_exceedsLoanLimit() throws Exception {
    // Premium member has limit of 5 books
    for (int i = 0; i < 5; i++) {
      Book book =
          new Book(
              "978013468599" + i,
              "Book " + i,
              "Author",
              "Publisher",
              2020,
              2,
              BookCategory.FICTION);
      libraryService.addBook(book);
      libraryService.issueBook("M001", "978013468599" + i);
    }

    Book extraBook =
        new Book(
            "9780134685995", "Extra Book", "Author", "Publisher", 2020, 1, BookCategory.FICTION);
    libraryService.addBook(extraBook);

    assertThatThrownBy(() -> libraryService.issueBook("M001", "9780134685995"))
        .isInstanceOf(LoanLimitExceededException.class);
  }

  @Test
  void testReturnBook_success() throws Exception {
    Loan loan = libraryService.issueBook("M001", "9780134685991");

    libraryService.returnBook(loan.getLoanId());

    Book book = libraryService.findBookByIsbn("9780134685991");
    assertThat(book.getAvailableCopies()).isEqualTo(5);
    assertThat(loan.getStatus()).isEqualTo(LoanStatus.RETURNED);
    assertThat(loan.getReturnDate()).isNotNull();
  }

  @Test
  void testReturnBook_loanNotFound() {
    assertThatThrownBy(() -> libraryService.returnBook("INVALID-LOAN-ID"))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("Loan not found");
  }

  @Test
  void testReturnBook_alreadyReturned() throws Exception {
    Loan loan = libraryService.issueBook("M001", "9780134685991");
    libraryService.returnBook(loan.getLoanId());

    assertThatThrownBy(() -> libraryService.returnBook(loan.getLoanId()))
        .isInstanceOf(InvalidInputException.class)
        .hasMessageContaining("not active");
  }

  @Test
  void testCalculateFine_noOverdue() throws Exception {
    Loan loan = libraryService.issueBook("M001", "9780134685991");
    double fine = libraryService.calculateFine(loan);
    assertThat(fine).isEqualTo(0.0);
  }

  @Test
  void testCalculateFine_withOverdue() {
    Loan overdueLoan =
        new Loan(
            "L001",
            "M001",
            "9780134685991",
            LocalDate.now().minusDays(30),
            LocalDate.now().minusDays(5));
    double fine = libraryService.calculateFine(overdueLoan);
    assertThat(fine).isGreaterThan(0.0);
  }

  @Test
  void testGetActiveLoansByMember() throws Exception {
    libraryService.issueBook("M001", "9780134685991");

    List<Loan> activeLoans = libraryService.getActiveLoansByMember("M001");
    assertThat(activeLoans).hasSize(1);
  }

  @Test
  void testGetActiveLoansByMember_noLoans() {
    List<Loan> activeLoans = libraryService.getActiveLoansByMember("M001");
    assertThat(activeLoans).isEmpty();
  }

  @Test
  void testGetOverdueLoans() throws Exception {
    // This test would need to manipulate loan dates to create overdue loans
    List<Loan> overdueLoans = libraryService.getOverdueLoans();
    assertThat(overdueLoans).isNotNull();
  }

  @Test
  void testGetLoanHistory() throws Exception {
    libraryService.issueBook("M001", "9780134685991");

    List<Loan> history = libraryService.getLoanHistory("M001");
    assertThat(history).hasSize(1);
  }

  @Test
  void testGetLoanHistory_noHistory() {
    List<Loan> history = libraryService.getLoanHistory("M001");
    assertThat(history).isEmpty();
  }

  // Statistics Tests

  @Test
  void testGetTotalBooks() {
    assertThat(libraryService.getTotalBooks()).isEqualTo(1);
  }

  @Test
  void testGetTotalMembers() {
    assertThat(libraryService.getTotalMembers()).isEqualTo(1);
  }

  @Test
  void testGetTotalActiveLoans() throws Exception {
    libraryService.issueBook("M001", "9780134685991");
    assertThat(libraryService.getTotalActiveLoans()).isEqualTo(1);
  }

  @Test
  void testGetTotalActiveLoans_afterReturn() throws Exception {
    Loan loan = libraryService.issueBook("M001", "9780134685991");
    libraryService.returnBook(loan.getLoanId());
    assertThat(libraryService.getTotalActiveLoans()).isEqualTo(0);
  }
}
