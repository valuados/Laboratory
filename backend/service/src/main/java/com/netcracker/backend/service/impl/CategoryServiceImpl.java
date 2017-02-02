package com.netcracker.backend.service.impl;

import com.netcracker.backend.dao.CategoryDao;
import com.netcracker.backend.exceptions.DaoException;
import com.netcracker.backend.exceptions.ServErrorCode;
import com.netcracker.backend.exceptions.ServException;
import com.netcracker.backend.service.CategoryService;
import com.netcracker.entities.Category;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private static Logger log = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    public CategoryServiceImpl() {
    }

    /**
     * This method getting all categories.
     *
     * @return categories.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Collection<Category> getAllCategories() throws ServException {
        Collection<Category> categories;
        try {
            categories = categoryDao.getCategoryListWithoutDetails();
            log.info("Getting all categories:" + categories);
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_000);
        }
        return categories;
    }

    /**
     * This method is used for getting all categories and its detailed information
     *
     * @return categories
     * @throws ServException catch DaoException, create new ServException and push up.
     */
    @Override
    public Collection<Category> getAllCategoriesDetailed() throws ServException {
        Collection<Category> categories;
        try {
            categories = categoryDao.getCategoryListDetailed();
            log.info("Getting all categories with details:" + categories);
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_000);
        }
        return categories;
    }

    /**
     * This method deleting category by id.
     *
     * @param id Category id.
     * @return true if category successfully is deleted.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Boolean deleteCategoryById(Integer id) throws ServException {
        Category category;
        try {
            category = categoryDao.get(id);
            categoryDao.delete(category);
            log.info("Deleting category:" + category);
            return true;
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_002);
        }
    }

    /**
     * This method saving new category.
     *
     * @param category
     * @return true if category is successfully saved.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Category saveCategory(Category category) throws ServException {
        try {
            Integer categoryId = categoryDao.add(category);
            log.info("Adding category:" + category);
            Category savedCategory = getCategoryById(categoryId);
            return savedCategory;
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_003);
        }
    }

    /**
     * This method getting category by id.
     *
     * @param id Category id.
     * @return category.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Category getCategoryById(Integer id) throws ServException {
        Category category;
        try {
            category = categoryDao.getCategoryWithoutDetailsById(id);
            log.info("Getting category:" + category);
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_004);
        }
        return category;
    }

    /**
     * This method updating category.
     *
     * @param category
     * @return true if category successfully is updated.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Category updateCategory(Integer id, Category category) throws ServException {
        try {
            if (id != null) {

                Category preparedCategory = Category
                        .builder()
                        .id(id)
                        .name(category.getName())
                        .offers(category.getOffers())
                        .build();

                categoryDao.update(preparedCategory);
                log.info("Updating category:" + category);
                return getCategoryById(id);
            }
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_006);
        } catch (ServException ex) {
            throw new ServException(ex, ServErrorCode.NC_SERV_004);
        }
        return null;
    }
}
