package com.example.bookshop.service;

import com.example.bookshop.entities.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    boolean isDataSeeded();
    void seedAuthors(List<Author> authors);

    Author getRandomAuthor();

    List<Author> getAllAuthorsWithBooksBeforeYear(LocalDate date);
    List<Author> getAllAuthorsOrderByBooksDesc();

}
