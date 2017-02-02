package com.netcracker.backend.service.impl;

import com.netcracker.backend.dao.OfferDao;
import com.netcracker.backend.exceptions.DaoException;
import com.netcracker.backend.exceptions.ServErrorCode;
import com.netcracker.backend.exceptions.ServException;
import com.netcracker.backend.service.OfferService;
import com.netcracker.entities.Offer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service("offerService")
@Transactional
public class OfferServiceImpl implements OfferService {
    private static Logger log = Logger.getLogger(OfferServiceImpl.class);

    @Autowired
    private OfferDao offerDao;

    public OfferServiceImpl() {
    }

    /**
     * This method getting all offers by category id.
     *
     * @param categoryId
     * @return offers.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Collection<Offer> getAllOffersByCategoryId(Integer categoryId) throws ServException {
        Collection<Offer> offers;
        try {
            offers = offerDao.getAllOffersByCategoryId(categoryId);
            log.info("Getting all offers by category id (" + categoryId + ") :" + offers);
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_000);
        }
        return offers;
    }

    /**
     * This method getting all offers.
     *
     * @return offers.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Collection<Offer> getAllOffers() throws ServException {
        Collection<Offer> offers;
        try {
            offers = offerDao.getAll();
            log.info("Getting all offers:" + offers);
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_000);
        }
        return offers;
    }

    /**
     * This method deleting offer by id.
     *
     * @param id Offer id.
     * @return true if offer successfully is deleted.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Boolean deleteOfferById(Integer id) throws ServException {
        Offer offer;
        try {
            offer = offerDao.get(id);
            offerDao.delete(offer);
            log.info("Deleting offer:" + offer);
            return true;
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_002);
        }
    }

    /**
     * This method saving new offer.
     *
     * @param offer
     * @return true if offer is successfully saved.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Offer saveOffer(Offer offer) throws ServException {
        try {
            Integer offerId = offerDao.add(offer);
            log.info("Adding offer:" + offer);
            Offer savedOffer = getOfferById(offerId);
            return savedOffer;
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_003);
        }
    }

    /**
     * This method getting offer by id.
     *
     * @param id Offer id.
     * @return offer.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Offer getOfferById(Integer id) throws ServException {
        Offer offer;
        try {
            offer = offerDao.get(id);
            log.info("Getting offer:" + offer);
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_004);
        }
        return offer;
    }

    /**
     * This method updating offer.
     *
     * @param offer
     * @return true if offer successfully is updated.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Offer updateOffer(Integer id, Offer offer) throws ServException {
        try {
            if (id != null) {

                Offer preparedOffer = Offer
                        .builder()
                        .id(id)
                        .name(offer.getName())
                        .components(offer.getComponents())
                        .build();

                offerDao.update(preparedOffer);
                log.info("Updating offer:" + offer);
                return getOfferById(id);
            }
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_006);
        } catch (ServException ex) {
            throw new ServException(ex, ServErrorCode.NC_SERV_004);
        }
        return null;
    }
}
