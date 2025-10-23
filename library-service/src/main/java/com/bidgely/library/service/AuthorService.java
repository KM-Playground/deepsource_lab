package com.bidgely.library.service;

import com.bidgely.library.exception.InvalidInputException;
import com.bidgely.library.model.Author;
import com.bidgely.library.util.IdGenerator;
import com.bidgely.library.util.ValidationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for managing authors.
 */
public class AuthorService {
    private final Map<String, Author> authors;

    public AuthorService() {
        this.authors = new HashMap<>();
    }

    /**
     * Adds a new author.
     */
    public Author addAuthor(String firstName, String lastName) {
        ValidationUtil.validateNotEmpty(firstName, "First name");
        ValidationUtil.validateNotEmpty(lastName, "Last name");

        String authorId = IdGenerator.generateAuthorId();
        Author author = new Author(authorId, firstName, lastName);
        authors.put(authorId, author);

        return author;
    }

    /**
     * Finds an author by ID.
     */
    public Author findAuthorById(String authorId) {
        return authors.get(authorId);
    }

    /**
     * Searches authors by name (first or last name).
     */
    public List<Author> searchAuthorsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String searchTerm = name.toLowerCase();
        return authors.values().stream()
                .filter(author -> author.getFirstName().toLowerCase().contains(searchTerm) ||
                        author.getLastName().toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());
    }

    /**
     * Gets all active authors.
     */
    public List<Author> getActiveAuthors() {
        return authors.values().stream()
                .filter(Author::isActive)
                .collect(Collectors.toList());
    }

    /**
     * Updates an author.
     */
    public Author updateAuthor(Author author) {
        if (author == null || author.getAuthorId() == null) {
            throw new InvalidInputException("Invalid author");
        }

        if (!authors.containsKey(author.getAuthorId())) {
            throw new InvalidInputException("Author not found: " + author.getAuthorId());
        }

        authors.put(author.getAuthorId(), author);
        return author;
    }

    /**
     * Deactivates an author.
     */
    public void deactivateAuthor(String authorId) {
        Author author = authors.get(authorId);
        if (author == null) {
            throw new InvalidInputException("Author not found: " + authorId);
        }

        author.setActive(false);
    }

    /**
     * Gets all authors.
     */
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors.values());
    }

    /**
     * Gets total number of authors.
     */
    public int getTotalAuthors() {
        return authors.size();
    }

    /**
     * Gets total number of active authors.
     */
    public int getTotalActiveAuthors() {
        return (int) authors.values().stream()
                .filter(Author::isActive)
                .count();
    }
}

