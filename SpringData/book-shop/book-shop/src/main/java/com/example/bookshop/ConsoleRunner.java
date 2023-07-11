package com.example.bookshop;
import com.example.bookshop.entities.Book;
import com.example.bookshop.service.AuthorService;
import com.example.bookshop.service.BookService;
import com.example.bookshop.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private BookService bookService;

    private AuthorService authorService;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAllData();


//        Get all books after the year 2000. Print only their titles.

//        System.out.println(this.bookService.getAllBooksAfterYear(LocalDate.of(1999, 1, 1))
//                .stream()
//                .map(Book::getTitle)
//                .collect(Collectors.joining("\n")));

//        Get all authors with at least one book with a release date before 1990. Print their first name and last name.

//        this.authorService.getAllAuthorsWithBooksBeforeYear(LocalDate.of(1990, 1,1));

//        3. Get all authors, ordered by the number of their books (descending). Print their first name, last name and
//book count.

//        this.authorService.getAllAuthorsOrderByBooksDesc();

//        4. Get all books from author George Powell, ordered by their release date (descending), then by book title
//(ascending). Print the book's title, release date and copies

        System.out.println(this.bookService.getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George", "Powell")
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.joining("\n")));

    }

}
