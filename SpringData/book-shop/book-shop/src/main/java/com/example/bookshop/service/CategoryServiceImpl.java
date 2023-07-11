package com.example.bookshop.service;

import com.example.bookshop.entities.Category;
import com.example.bookshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isDataSeeded() {

        return this.categoryRepository.count() > 0;
    }
    @Override
    public void seedCategories(List<Category> categories) {
        this.categoryRepository.saveAllAndFlush(categories);

    }

    @Override
    public Category getRandomCategory() {
        long count = this.categoryRepository.count();

        if(count != 0) {
            long randomId = new Random().nextLong(1L, count) + 1L;
            return this.categoryRepository.findById(randomId).orElseThrow(NoSuchElementException::new);
        }

        throw new RuntimeException();
    }
    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        for (int i = 0; i < this.categoryRepository.count() / 2 ; i++) {
             categories.add(getRandomCategory());
        }

        return  categories;

    }
}


