package com.netcracker.backend.dao.impl;

import com.netcracker.backend.dao.OfferDao;
import com.netcracker.backend.exceptions.DaoException;
import com.netcracker.entities.Offer;

import java.util.Collection;

public class OfferDaoImpl extends DaoImpl<Offer, Integer> implements OfferDao{

    private static final String OFFERS_BY_CATEGORY_ID_QUERY = "select new Offer(o.id, o.categoryId, o.name) from Offer o where o.categoryId = :id";
    private static final String OFFER_BY_ID_QUERY = "select new Offer(o.id, o.categoryId, o.name) from Offer o where o.id = :id";

    public OfferDaoImpl(Class type) {
        super(type);
    }

    @Override
    public Collection<Offer> getAllOffersByCategoryId(Integer categoryId) throws DaoException {
        return getQuery(OFFERS_BY_CATEGORY_ID_QUERY).setParameter("id", categoryId).list();
    }

    @Override
    public Offer getOfferById(Integer offerId) throws DaoException {
        return (Offer) getQuery(OFFER_BY_ID_QUERY).setParameter("id", offerId).uniqueResult();
    }

}
