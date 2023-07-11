package com.example.bookshop.service;

import com.example.bookshop.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    boolean isDataSeeded();
    void seedBooks(List<Book> books);

    List<Book> getAllBooksAfterYear(LocalDate date);
    List<Book> getAllBooksBeforeYear(LocalDate date);

    List<Book> getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);
}
