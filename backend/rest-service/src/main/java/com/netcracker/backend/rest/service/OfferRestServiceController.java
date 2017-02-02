package com.netcracker.backend.rest.service;

import com.netcracker.backend.exceptions.ServException;
import com.netcracker.backend.service.OfferService;
import com.netcracker.entities.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api")
public class OfferRestServiceController {

    @Autowired
    OfferService offerService;

    @GetMapping("/offers")
    public Collection getOffers() throws ServException {
        return offerService.getAllOffers();
    }

    @GetMapping("/offers/{categoryId}")
    public Collection<Offer> getOffersByCategoryId(@PathVariable Integer categoryId) throws ServException {
        return offerService.getAllOffersByCategoryId(categoryId);
    }

    @GetMapping(value = "/offer/{id}")
    public Offer getStudentById(@PathVariable Integer id) throws ServException {
        return offerService.getOfferById(id);
    }

    @PostMapping(value = "/save-offer")
    ResponseEntity<Offer> addOffer(@RequestBody Offer offer) throws ServException {
        Offer savedOffer = offerService.saveOffer(offer);
        return new ResponseEntity(savedOffer, HttpStatus.OK);
    }

    @DeleteMapping("/offer/delete/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable Integer id) {
        try {
            offerService.deleteOfferById(id);
            return new ResponseEntity(id, HttpStatus.OK);
        } catch (ServException e) {
            e.printStackTrace();
            return new ResponseEntity("No Student found for ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/offer/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Integer id, @RequestBody Offer offer) {
        try {
            Offer updatedOffer = offerService.updateOffer(id, offer);
            return new ResponseEntity(updatedOffer, HttpStatus.OK);
        } catch (ServException e) {
            e.printStackTrace();
            return new ResponseEntity("Could not update student with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
