package com.netcracker.laboratory.services;

import com.google.common.collect.Lists;
import com.netcracker.entities.Offer;
import com.netcracker.entities.Student;

import java.util.Collection;

/**
 * Created by valua on 10/23/2016.
 */
public class RestCartWrapper {
    private RestTemplateWrapper restTemplate;

    public RestCartWrapper(RestTemplateWrapper restTemplateWrapper) {
        this.restTemplate = restTemplateWrapper;
    }

    public Collection<Offer> getAllOffers() {
        return Lists.newArrayList(restTemplate.getForObject("/offers", Offer[].class));
    }

    public Offer getOffer(Integer offerID) {
        return restTemplate.getForObject("/offer/{offerId}", Offer.class, offerID);
    }

    public Offer addOffer(Offer offer) {
        return restTemplate.postForObject("/save-offer", offer, Offer.class);
    }

    public void deleteOffer(Integer offerId) {
        restTemplate.delete("/offer/delete/{offerId}", offerId);
    }

    public void updateOffer(Integer offerId, Offer offer) {
        restTemplate.put("/student/{studentId}",offer, offerId);
    }

}
