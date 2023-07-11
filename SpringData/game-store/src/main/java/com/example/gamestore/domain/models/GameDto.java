package com.example.gamestore.domain.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


public class GameDto {

    private String title;
    private BigDecimal price;
    private float size;
    private String trailer;
    private String thumbnailURL;
    private String description;
    private LocalDate releaseDate;

    public GameDto(String title, BigDecimal price, float size, String trailer, String thumbnailURL, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailURL = thumbnailURL;
        this.description = description;
        this.releaseDate = releaseDate;

    }

    private void validate() {
// Title – has to begin with an uppercase letter and must have length between 3 and 100 symbols
//(inclusively).
//o Price – must be a positive number.
//o Size – must be a positive number.
//o Trailer – only videos from YouTube are allowed. Only their ID, which is a string of exactly 11
//characters, should be saved to the database.
//For example, if the URL to the trailer is https://www.youtube.com/watch?v=edYCtaNueQY,
//the required part that must be saved into the database is edYCtaNueQY. That would be always the
//last 11 characters from the provided URL.
//o Thumbnail URL – it should be a plain text starting with http://, https://
//o Description – must be at least 20 symbols
//        if (!title.matches("^[A-Z].{2,99}$")) {
//            throw new IllegalArgumentException("Title should begin with an uppercase letter and have a length between 3 and 100 characters.");
//        }
//
//        if (price.compareTo(BigDecimal.ZERO) <= 0) {
//            throw new IllegalArgumentException("Price must be a positive number.");
//        }
//
//        if (size <= 0) {
//            throw new IllegalArgumentException("Size must be a positive number.");
//        }

//        if (!trailer.matches("^.*(?:youtu.be\\/|v\\/|u\\/\\w\\/|embed\\/|watch\\?v=|v=|youtu.be\\?v=)([^#\\&\\?]*).*")) {
//            throw new IllegalArgumentException("Trailer URL should be from YouTube and contain a valid video ID.");
//        }

//        if (!thumbnailURL.matches("^https?:\\/\\/.*")) {
//            throw new IllegalArgumentException("Thumbnail URL should start with 'http://' or 'https://'.");
//        }
//
//        if (description.length() < 20) {
//            throw new IllegalArgumentException("Description should have at least 20 characters.");
//        }
    }
    @Pattern(regexp = "[A-Z][\\w\\d\\s\\.]{3,100}", message = "Title – has to begin with an uppercase letter and must have length between 3 and 100 symbols ")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DecimalMin(value = "0", message = "Must be a positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Positive(message = "Must be a positive number")
    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    @Size(min = 11, max = 11, message = "Enter valid trailer. Exactly 11 characters")
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "(https?\\:\\/\\/.*)", message = "Enter valid path. Must starting with http://, https://")
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Size(min = 20, message = "Enter valid description. Must be at least 20 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String successfullyAddedGame() {
        return "Added " + this.title;
    }
}
