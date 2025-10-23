package com.bidgely.library.model;

import java.util.Objects;

/** Represents a book in the library system. */
public class Book {
  private String isbn;
  private String title;
  private String author;
  private String publisher;
  private int publicationYear;
  private int totalCopies;
  private int availableCopies;
  private BookCategory category;

  public Book(
      String isbn,
      String title,
      String author,
      String publisher,
      int publicationYear,
      int totalCopies,
      BookCategory category) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.publicationYear = publicationYear;
    this.totalCopies = totalCopies;
    this.availableCopies = totalCopies;
    this.category = category;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public int getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(int publicationYear) {
    this.publicationYear = publicationYear;
  }

  public int getTotalCopies() {
    return totalCopies;
  }

  public void setTotalCopies(int totalCopies) {
    this.totalCopies = totalCopies;
  }

  public int getAvailableCopies() {
    return availableCopies;
  }

  public void setAvailableCopies(int availableCopies) {
    this.availableCopies = availableCopies;
  }

  public BookCategory getCategory() {
    return category;
  }

  public void setCategory(BookCategory category) {
    this.category = category;
  }

  public boolean isAvailable() {
    return availableCopies > 0;
  }

  public void borrowCopy() {
    if (availableCopies > 0) {
      availableCopies--;
    }
  }

  public void returnCopy() {
    if (availableCopies < totalCopies) {
      availableCopies++;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(isbn, book.isbn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn);
  }

  @Override
  public String toString() {
    return "Book{"
        + "isbn='"
        + isbn
        + '\''
        + ", title='"
        + title
        + '\''
        + ", author='"
        + author
        + '\''
        + ", publisher='"
        + publisher
        + '\''
        + ", publicationYear="
        + publicationYear
        + ", totalCopies="
        + totalCopies
        + ", availableCopies="
        + availableCopies
        + ", category="
        + category
        + '}';
  }
}
