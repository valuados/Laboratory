package com.netcracker.backend.dao.impl;

import com.netcracker.backend.dao.CategoryDao;
import com.netcracker.backend.exceptions.DaoException;
import com.netcracker.entities.Category;

import java.util.Collection;

public class CategoryDaoImpl extends DaoImpl<Category, Integer> implements CategoryDao {

    private static final String WITHOUT_DETAILS_QUERY = "select new Category(c.id, c.name) from Category c";
    private static final String ALL_CATEGORIES_DETAILED_QUERY="select c from Category as c left join c.offers";
    private static final String WITHOUT_DETAILS_BY_ID_QUERY = "select new Category(c.id, c.name) from Category c where c.id = :id";

    public CategoryDaoImpl(Class type) {
        super(type);
    }

    @Override
    public Collection<Category> getCategoryListWithoutDetails() throws DaoException {
        return getQuery(WITHOUT_DETAILS_QUERY).list();
    }

    @Override
    public Collection<Category> getCategoryListDetailed() throws DaoException {
        return getQuery(ALL_CATEGORIES_DETAILED_QUERY).list();
    }

    @Override
    public Category getCategoryWithoutDetailsById(Integer id) throws DaoException {
        return (Category) getQuery(WITHOUT_DETAILS_BY_ID_QUERY).setParameter("id", id).uniqueResult();
    }

}
