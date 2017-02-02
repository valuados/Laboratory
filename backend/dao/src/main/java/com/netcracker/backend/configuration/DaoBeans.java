package com.netcracker.backend.configuration;

import com.netcracker.backend.dao.CategoryDao;
import com.netcracker.backend.dao.Dao;
import com.netcracker.backend.dao.OfferDao;
import com.netcracker.backend.dao.SalesOrderDao;
import com.netcracker.backend.dao.impl.CategoryDaoImpl;
import com.netcracker.backend.dao.impl.DaoImpl;
import com.netcracker.backend.dao.impl.OfferDaoImpl;
import com.netcracker.backend.dao.impl.SalesOrderDaoImpl;
import com.netcracker.entities.Category;
import com.netcracker.entities.Offer;
import com.netcracker.entities.SalesOrder;
import com.netcracker.entities.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({"com.netcracker.backend.dao"})
public class DaoBeans {

    /**
     * Create studentDao Bean.
     *
     * @return studentDao Bean.
     */
    @Bean(name = "studentDao")
    public Dao<Student, Integer> studentDao() {
        return new DaoImpl<Student, Integer>(Student.class);
    }

    @Bean(name = "categoryDao")
    public CategoryDao categoryDao() { return new CategoryDaoImpl(Category.class);}

    @Bean(name = "offerDao")
    public OfferDao offerDao() { return new OfferDaoImpl(Offer.class);}

    @Bean(name = "salesOrderDao")
    public SalesOrderDao salesOrderDao() { return new SalesOrderDaoImpl(SalesOrder.class);}

}
