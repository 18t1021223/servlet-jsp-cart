package com.vn.service;

import com.vn.model.Category;
import com.vn.repository.CategoryRepository;

import java.util.List;

public class CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepository();

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
