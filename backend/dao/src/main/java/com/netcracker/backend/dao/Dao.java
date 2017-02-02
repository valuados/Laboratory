package com.netcracker.backend.dao;

import com.netcracker.backend.exceptions.DaoException;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ilkh0715 on 30.10.2016.
 */
public interface Dao<T, PK extends Serializable> {

    T get(PK id) throws DaoException;

    List<T> getAll() throws DaoException;

    PK add(T object) throws DaoException;

    void update(T object) throws DaoException;

    void delete(T object) throws DaoException;

    Query getQuery(String hql) throws DaoException;

}