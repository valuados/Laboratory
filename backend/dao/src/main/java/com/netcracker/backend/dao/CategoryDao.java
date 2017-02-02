package com.netcracker.backend.dao;

import com.netcracker.backend.exceptions.DaoException;
import com.netcracker.entities.Category;

import java.util.Collection;

public interface CategoryDao extends Dao<Category, Integer> {
    Category getCategoryWithoutDetailsById(Integer id) throws DaoException;
    Collection<Category> getCategoryListWithoutDetails() throws DaoException;
    Collection<Category> getCategoryListDetailed() throws DaoException;
}
