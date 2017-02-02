package com.netcracker.backend.rest.service;

import com.netcracker.backend.exceptions.ServException;
import com.netcracker.backend.service.SalesOrderService;
import com.netcracker.entities.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api")
public class SalesOrderRestServiceController {

    @Autowired
    SalesOrderService salesOrderService;

    @GetMapping("/sales-orders")
    public Collection getOffers() throws ServException {
        return salesOrderService.getAllSalesOrders();
    }

    @GetMapping(value = "/sales-order/{id}")
    public SalesOrder getSalesOrderById(@PathVariable Integer id) throws ServException {
        return salesOrderService.getSalesOrderById(id);
    }

    @PostMapping(value = "/save-sales-order")
    ResponseEntity<SalesOrder> addSalesOrder(@RequestBody SalesOrder salesOrder) throws ServException {
        SalesOrder savedSalesOrder = salesOrderService.saveSalesOrder(salesOrder);
        return new ResponseEntity(savedSalesOrder, HttpStatus.OK);
    }

    @DeleteMapping("/sales-order/delete/{id}")
    public ResponseEntity<SalesOrder> deleteSalesOrder(@PathVariable Integer id) {
        try {
            salesOrderService.deleteSalesOrderById(id);
            return new ResponseEntity(id, HttpStatus.OK);
        } catch (ServException e) {
            e.printStackTrace();
            return new ResponseEntity("No sales order found for ID " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/sales-order/{id}")
    public ResponseEntity<SalesOrder> updateSalesOrder(@PathVariable Integer id, @RequestBody SalesOrder salesOrder) {
        try {
            SalesOrder updatedSalesOrder = salesOrderService.updateSalesOrder(id, salesOrder);
            return new ResponseEntity(updatedSalesOrder, HttpStatus.OK);
        } catch (ServException e) {
            e.printStackTrace();
            return new ResponseEntity("Could not update sales order with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sales-order/create/{id}")
    public ResponseEntity<SalesOrder> createSalesOrder(@PathVariable Integer id)  {
        try {
            return new ResponseEntity(salesOrderService.createSalesOrder(id), HttpStatus.OK);
        } catch (ServException e) {
            e.printStackTrace();
            return new ResponseEntity("Could not create sales order", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sales-order/add-order/{offerId}")
        public ResponseEntity<SalesOrder> addOrder(@RequestBody SalesOrder salesOrder, @PathVariable Integer offerId) throws IOException {
        try {
            salesOrderService.addOrderToSalesOrder(salesOrder, offerId);
            return new ResponseEntity(salesOrder, HttpStatus.OK);
        } catch (ServException e) {
            e.printStackTrace();
            return new ResponseEntity("Could not add order", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sales-order/delete-order/{orderId}")
    public ResponseEntity<SalesOrder> deleteOrder(@RequestBody SalesOrder salesOrder, @PathVariable Integer orderId) throws IOException {
        try {
            salesOrderService.deleteOrderFromSalesOrder(salesOrder, orderId);
            return new ResponseEntity(salesOrder, HttpStatus.OK);
        } catch (ServException e) {
            e.printStackTrace();
            return new ResponseEntity("Could not add order", HttpStatus.NOT_FOUND);
        }
    }
}
