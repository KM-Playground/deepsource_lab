package com.bidgely.library.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {

  private Book book;

  @BeforeEach
  void setUp() {
    book =
        new Book(
            "978-0-13-468599-1",
            "Effective Java",
            "Joshua Bloch",
            "Addison-Wesley",
            2018,
            5,
            BookCategory.TECHNOLOGY);
  }

  @Test
  void testBookCreation() {
    assertThat(book.getIsbn()).isEqualTo("978-0-13-468599-1");
    assertThat(book.getTitle()).isEqualTo("Effective Java");
    assertThat(book.getAuthor()).isEqualTo("Joshua Bloch");
    assertThat(book.getPublisher()).isEqualTo("Addison-Wesley");
    assertThat(book.getPublicationYear()).isEqualTo(2018);
    assertThat(book.getTotalCopies()).isEqualTo(5);
    assertThat(book.getAvailableCopies()).isEqualTo(5);
    assertThat(book.getCategory()).isEqualTo(BookCategory.TECHNOLOGY);
  }

  @Test
  void testIsAvailable_whenCopiesAvailable() {
    assertThat(book.isAvailable()).isTrue();
  }

  @Test
  void testIsAvailable_whenNoCopiesAvailable() {
    book.setAvailableCopies(0);
    assertThat(book.isAvailable()).isFalse();
  }

  @Test
  void testBorrowCopy() {
    int initialCopies = book.getAvailableCopies();
    book.borrowCopy();
    assertThat(book.getAvailableCopies()).isEqualTo(initialCopies - 1);
  }

  @Test
  void testBorrowCopy_whenNoCopiesAvailable() {
    book.setAvailableCopies(0);
    book.borrowCopy();
    assertThat(book.getAvailableCopies()).isEqualTo(0);
  }

  @Test
  void testReturnCopy() {
    book.setAvailableCopies(3);
    book.returnCopy();
    assertThat(book.getAvailableCopies()).isEqualTo(4);
  }

  @Test
  void testReturnCopy_whenAllCopiesAvailable() {
    book.returnCopy();
    assertThat(book.getAvailableCopies()).isEqualTo(5);
  }

  @Test
  void testEquals_sameIsbn() {
    Book book2 =
        new Book(
            "978-0-13-468599-1",
            "Different Title",
            "Different Author",
            "Different Publisher",
            2020,
            3,
            BookCategory.FICTION);
    assertThat(book).isEqualTo(book2);
  }

  @Test
  void testEquals_differentIsbn() {
    Book book2 =
        new Book(
            "978-0-13-468599-2",
            "Effective Java",
            "Joshua Bloch",
            "Addison-Wesley",
            2018,
            5,
            BookCategory.TECHNOLOGY);
    assertThat(book).isNotEqualTo(book2);
  }

  @Test
  void testHashCode() {
    Book book2 =
        new Book(
            "978-0-13-468599-1",
            "Different Title",
            "Different Author",
            "Different Publisher",
            2020,
            3,
            BookCategory.FICTION);
    assertThat(book.hashCode()).isEqualTo(book2.hashCode());
  }

  @Test
  void testToString() {
    String result = book.toString();
    assertThat(result).contains("978-0-13-468599-1");
    assertThat(result).contains("Effective Java");
    assertThat(result).contains("Joshua Bloch");
  }

  @Test
  void testSetters() {
    book.setTitle("New Title");
    book.setAuthor("New Author");
    book.setPublisher("New Publisher");
    book.setPublicationYear(2020);
    book.setTotalCopies(10);
    book.setCategory(BookCategory.SCIENCE);

    assertThat(book.getTitle()).isEqualTo("New Title");
    assertThat(book.getAuthor()).isEqualTo("New Author");
    assertThat(book.getPublisher()).isEqualTo("New Publisher");
    assertThat(book.getPublicationYear()).isEqualTo(2020);
    assertThat(book.getTotalCopies()).isEqualTo(10);
    assertThat(book.getCategory()).isEqualTo(BookCategory.SCIENCE);
  }
}
