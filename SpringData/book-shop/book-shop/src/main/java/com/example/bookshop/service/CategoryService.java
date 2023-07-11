package com.example.bookshop.service;

import com.example.bookshop.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    boolean isDataSeeded();
    void seedCategories(List<Category> categories);

    Category getRandomCategory();

    Set<Category> getRandomCategories();
}