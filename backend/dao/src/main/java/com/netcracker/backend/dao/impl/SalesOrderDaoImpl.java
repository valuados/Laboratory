package com.netcracker.backend.dao.impl;

import com.netcracker.backend.dao.SalesOrderDao;
import com.netcracker.entities.SalesOrder;

public class SalesOrderDaoImpl extends DaoImpl<SalesOrder, Integer> implements SalesOrderDao {

    public SalesOrderDaoImpl(Class type) {
        super(type);
    }
}
