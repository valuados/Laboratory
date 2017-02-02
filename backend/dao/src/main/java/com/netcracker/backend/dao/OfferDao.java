package com.netcracker.backend.dao;

import com.netcracker.backend.exceptions.DaoException;
import com.netcracker.entities.Offer;

import java.util.Collection;

public interface OfferDao extends Dao<Offer, Integer> {
    Collection<Offer> getAllOffersByCategoryId(Integer categoryId) throws DaoException;
    Offer getOfferById(Integer offerId) throws DaoException;
}