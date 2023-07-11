package com.example.bookshop.service;

import com.example.bookshop.entities.Book;
import com.example.bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isDataSeeded() {

        return this.bookRepository.count() > 0;
    }

    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAllAndFlush(books);

    }

    @Override
    public List<Book> getAllBooksAfterYear(LocalDate date) {
        List<Book> allByReleaseDateAfter = this.bookRepository.findAllByReleaseDateAfter(date).get();

        return allByReleaseDateAfter;
    }

    @Override
    public List<Book> getAllBooksBeforeYear(LocalDate date) {
        List<Book> books = this.bookRepository
                .findAllByReleaseDateBefore(date)
                .orElseThrow(NoSuchElementException::new);

//        books.forEach(b -> System.out.println(b.getBookTitleEditionTypeAndPrice()));

        return books;
    }

    @Override
    public List<Book> getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName) {
        return this.bookRepository
                .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName);
    }


}
