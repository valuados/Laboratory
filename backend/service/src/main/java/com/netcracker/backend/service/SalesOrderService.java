package com.netcracker.backend.service;

import com.netcracker.backend.exceptions.ServException;
import com.netcracker.entities.Offer;
import com.netcracker.entities.SalesOrder;

import java.util.Collection;

public interface SalesOrderService {

    Collection<SalesOrder> getAllSalesOrders() throws ServException;

    Boolean deleteSalesOrderById(Integer id) throws ServException;

    SalesOrder saveSalesOrder(SalesOrder salesOrder) throws ServException;

    SalesOrder getSalesOrderById(Integer id) throws ServException;

    SalesOrder updateSalesOrder(Integer id, SalesOrder salesOrder) throws ServException;

    SalesOrder createSalesOrder(Integer offerId) throws ServException;

    SalesOrder addOrderToSalesOrder(SalesOrder salesOrder, Integer offerId) throws ServException;

    SalesOrder deleteOrderFromSalesOrder (SalesOrder salesOrder, Integer offerId) throws ServException;
}
