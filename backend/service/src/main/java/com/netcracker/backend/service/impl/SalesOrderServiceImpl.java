package com.netcracker.backend.service.impl;

import com.netcracker.backend.dao.OfferDao;
import com.netcracker.backend.dao.SalesOrderDao;
import com.netcracker.backend.exceptions.DaoException;
import com.netcracker.backend.exceptions.ServErrorCode;
import com.netcracker.backend.exceptions.ServException;
import com.netcracker.backend.service.IdGenerator;
import com.netcracker.backend.service.SalesOrderService;
import com.netcracker.entities.Offer;
import com.netcracker.entities.Order;
import com.netcracker.entities.SalesOrder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

@Service
@Transactional
public class SalesOrderServiceImpl implements SalesOrderService {
    private static Logger log = Logger.getLogger(SalesOrderServiceImpl.class);

    @Autowired
    private SalesOrderDao salesOrderDao;

    @Autowired
    private OfferDao offerDao;

    @Autowired
    @Qualifier("pseudoRandomGenerator")
    private IdGenerator idGenerator;

    public SalesOrderServiceImpl() {
    }

    /**
     * This method getting all sales orders.
     *
     * @return salesOrders.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Collection<SalesOrder> getAllSalesOrders() throws ServException {
        Collection<SalesOrder> salesOrders;
        try {
            salesOrders = salesOrderDao.getAll();
            log.info("Getting all sales orders:" + salesOrders);
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_000);
        }
        return salesOrders;
    }

    /**
     * This method deleting sales order by id.
     *
     * @param id SalesOrder id.
     * @return true if sales order successfully is deleted.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public Boolean deleteSalesOrderById(Integer id) throws ServException {
        SalesOrder salesOrder;
        try {
            salesOrder = salesOrderDao.get(id);
            salesOrderDao.delete(salesOrder);
            log.info("Deleting sales order:" + salesOrder);
            return true;
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_002);
        }
    }

    /**
     * This method saving new sales order.
     *
     * @param salesOrder
     * @return true if sales order is successfully saved.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public SalesOrder saveSalesOrder(SalesOrder salesOrder) throws ServException {
        try {
            Integer salesOrderId = salesOrderDao.add(salesOrder);
            log.info("Adding sales order:" + salesOrder);
            SalesOrder savedSalesOrder = getSalesOrderById(salesOrderId);
            return savedSalesOrder;
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_003);
        }
    }

    /**
     * This method getting sales order by id.
     *
     * @param id SalesOrder id.
     * @return salesOrder.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public SalesOrder getSalesOrderById(Integer id) throws ServException {
        SalesOrder salesOrder;
        try {
            salesOrder = salesOrderDao.get(id);
            log.info("Getting sales order:" + salesOrder);
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_004);
        }
        return salesOrder;
    }

    /**
     * This method updating sales order.
     *
     * @param salesOrder
     * @return true if sales order successfully is updated.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public SalesOrder updateSalesOrder(Integer id, SalesOrder salesOrder) throws ServException {
        try {
            if (id != null) {

                SalesOrder preparedSalesOrder = SalesOrder
                        .builder()
                        .id(id)
                        .orders(salesOrder.getOrders())
                        .build();

                salesOrderDao.update(preparedSalesOrder);
                log.info("Updating sales order:" + salesOrder);
                return getSalesOrderById(id);
            }
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_006);
        } catch (ServException ex) {
            throw new ServException(ex, ServErrorCode.NC_SERV_004);
        }
        return null;
    }

    /**
     * This method creating sales order.
     *
     * @param offerId
     * @return created sales order.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public SalesOrder createSalesOrder(Integer offerId) throws ServException {
        try {
            Offer initOffer = offerDao.getOfferById(offerId);

            SalesOrder salesOrder = SalesOrder
                    .builder()
                    .id(idGenerator.generate())
                    .orders(new ArrayList<>())
                    .build();

            Order initOrder = Order
                    .builder()
                    .id(idGenerator.generate())
                    .salesOrderId(salesOrder.getId())
                    .offer(initOffer)
                    .orderComponents(new ArrayList<>())
                    .build();

            salesOrder.getOrders().add(initOrder);

            return salesOrder;
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_006);
        }
    }

    /**
     * This method adds offer to sales order.
     *
     * @param salesOrder
     * @param offerId
     * @return updated sales order.
     * @throws ServException catch DaoException, create new ServException and pushing up.
     */
    @Override
    public SalesOrder addOrderToSalesOrder(SalesOrder salesOrder, Integer offerId) throws ServException {
        try {
            if (salesOrder.getOrders() == null) {
                salesOrder.setOrders(new ArrayList<>());
            }

            Offer offer = offerDao.getOfferById(offerId);
            Order createdOrder = Order
                    .builder()
                    .id(idGenerator.generate())
                    .salesOrderId(salesOrder.getId())
                    .offer(offer)
                    .orderComponents(new ArrayList<>())
                    .build();

            Iterator<Order> iterator = salesOrder.getOrders().iterator();

            while (iterator.hasNext()) {
                if (iterator.next().getOffer().getCategoryId().intValue() == offer.getCategoryId()) {
                    iterator.remove();
                }
            }

            salesOrder.getOrders().add(createdOrder);

            return salesOrder;
        } catch (DaoException e) {
            throw new ServException(e, ServErrorCode.NC_SERV_006);
        }
    }

    @Override
    public SalesOrder deleteOrderFromSalesOrder(SalesOrder salesOrder, Integer offerId) throws ServException {
        Iterator<Order> iterator = salesOrder.getOrders().iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getId().intValue() == offerId.intValue()) {
                iterator.remove();
            }
        }

        return salesOrder;
    }
}
