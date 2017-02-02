package com.netcracker.backend.service;

import com.netcracker.backend.exceptions.ServException;
import com.netcracker.entities.Offer;

import java.util.Collection;

public interface OfferService {
    Collection<Offer> getAllOffersByCategoryId(Integer categoryId) throws ServException;

    Collection<Offer> getAllOffers() throws ServException;

    Boolean deleteOfferById(Integer id) throws ServException;

    Offer saveOffer(Offer offer) throws ServException;

    Offer getOfferById(Integer id) throws ServException;

    Offer updateOffer(Integer id, Offer offer) throws ServException;
}
