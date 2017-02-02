package com.netcracker.backend.rest.service;

import com.netcracker.backend.exceptions.ServException;
import com.netcracker.backend.service.CategoryService;
import com.netcracker.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api")
public class CategoryRestServiceController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public Collection getCategories() throws ServException {

        return categoryService.getAllCategories();
    }
    @GetMapping("/categories-full")
    public Collection getCategoriesDetailed() throws ServException {

        return categoryService.getAllCategoriesDetailed();
    }

    @GetMapping(value = "/category/{id}")
    public Category getCategoryById(@PathVariable Integer id) throws ServException {
        return categoryService.getCategoryById(id);
    }

    @PostMapping(value = "/save-category")
    ResponseEntity<Category> addCategory(@RequestBody Category category) throws ServException {
        Category savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity(savedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
        try {
            categoryService.deleteCategoryById(id);
            return new ResponseEntity(id, HttpStatus.OK);
        } catch (ServException e) {
            e.printStackTrace();
            return new ResponseEntity("No Student found for ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.updateCategory(id, category);
            return new ResponseEntity(updatedCategory, HttpStatus.OK);
        } catch (ServException e) {
            e.printStackTrace();
            return new ResponseEntity("Could not update student with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
