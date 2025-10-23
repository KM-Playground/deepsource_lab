package com.bidgely.library.model;

import java.time.LocalDate;
import java.util.Objects;

/** Represents a book author. */
public class Author {
  private String authorId;
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private String nationality;
  private String biography;
  private boolean active;

  public Author(String authorId, String firstName, String lastName) {
    this.authorId = authorId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.active = true;
  }

  public String getAuthorId() {
    return authorId;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public String getBiography() {
    return biography;
  }

  public void setBiography(String biography) {
    this.biography = biography;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Author author = (Author) o;
    return Objects.equals(authorId, author.authorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorId);
  }

  @Override
  public String toString() {
    return "Author{"
        + "authorId='"
        + authorId
        + '\''
        + ", fullName='"
        + getFullName()
        + '\''
        + ", nationality='"
        + nationality
        + '\''
        + '}';
  }
}
