package com.example.bookshop.service;

import com.example.bookshop.entities.Author;
import com.example.bookshop.entities.Book;
import com.example.bookshop.entities.Category;
import com.example.bookshop.enums.AgeRestriction;
import com.example.bookshop.enums.EditionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.bookshop.constants.FilePath.*;
//to populate the database

@Service
public class SeedServiceImpl implements SeedService {

    private CategoryService categoryService;
    private AuthorService authorService;

    private BookService bookService;

    @Autowired
    public SeedServiceImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void seedAuthors() throws IOException {

        if (this.authorService.isDataSeeded()) {
            return;
        }
     this.authorService.seedAuthors(
             Files.readAllLines(Path.of(RESOURCE_URL + AUTHOR_FILE_NAME))
             .stream()
             .map(firstAndLastName -> {
                 String[] firstAndLastNameArgs = firstAndLastName.split(" ");
                 String firstName = firstAndLastNameArgs[0];
                 String lastName = firstAndLastNameArgs[1];
                 return new Author(firstName, lastName);
             }).toList());
        ;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookService.isDataSeeded()) {
            return;
        }

        List<Book> bookStream = Files.readAllLines(Path.of(RESOURCE_URL + BOOK_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(row -> {
                    String[] arg = row.split("\\s+");

                    Author randomAuthor = this.authorService.getRandomAuthor();
                    Set<Category> randomCategories = this.categoryService.getRandomCategories();
                    String title = Arrays.stream(arg)
                            .skip(5)
                            .collect(Collectors.joining());

                    EditionType editionType = EditionType.values()[Integer.parseInt(arg[0])];
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(arg[4])];
                    LocalDate releaseDate = LocalDate.parse(arg[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                    int copies = Integer.parseInt(arg[2]);
                    BigDecimal price = new BigDecimal(arg[3]);

                    return Book.builder()
                            .author(randomAuthor)
                            .categories(randomCategories)
                            .title(title)
                            .editionType(editionType)
                            .ageRestriction(ageRestriction)
                            .releaseDate(releaseDate)
                            .copies(copies)
                            .price(price).build();

                }).toList();

        this.bookService.seedBooks(bookStream);

    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryService.isDataSeeded()) {
            return;
        }
        this.categoryService.seedCategories(
                Files.readAllLines(Path.of(RESOURCE_URL + CATEGORY_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(Category::new)
                .toList());

    }
}
