package com.netcracker.backend.service;

import com.netcracker.backend.exceptions.ServException;
import com.netcracker.entities.Category;

import java.util.Collection;

public interface CategoryService {
    Collection<Category> getAllCategories() throws ServException;

    Collection<Category> getAllCategoriesDetailed() throws ServException;

    Boolean deleteCategoryById(Integer id) throws ServException;

    Category saveCategory(Category student) throws ServException;

    Category getCategoryById(Integer id) throws ServException;

    Category updateCategory(Integer id, Category category) throws ServException;
}
