package com.netcracker.laboratory.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.netcracker.entities.Category;
import com.netcracker.entities.Student;
import com.netcracker.entities.Offer;
import com.netcracker.entities.SalesOrder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RestServiceWrapper {

    private RestTemplateWrapper restTemplate;

    public RestServiceWrapper(RestTemplateWrapper restTemplateWrapper) {
        this.restTemplate = restTemplateWrapper;
    }

    public Collection<Student> getAllStudents() {
        return Lists.newArrayList(restTemplate.getForObject("/students", Student[].class));
    }

    public Collection<Category> getAllCategories() {
        return Lists.newArrayList(restTemplate.getForObject("/categories", Category[].class));
    }

    public Category getCategoryById(Integer categoryId) {
        return restTemplate.getForObject("/category/{id}", Category.class, categoryId);
    }

    public Collection<Offer> getAllOffersByCategoryId(Integer categoryId) {
        return Lists.newArrayList(restTemplate.getForObject("/offers/{categoryId}", Offer[].class, categoryId));
    }

    public Collection<Offer> getAllOffers() {
        return Lists.newArrayList(restTemplate.getForObject("/offers", Offer[].class));
    }

    public Student getStudent(Integer studentId) {
        return restTemplate.getForObject("/student/{studentId}", Student.class, studentId);
    }

    public Student addStudent(Student student) {
        return restTemplate.postForObject("/save-student", student, Student.class);
    }

    public SalesOrder createSalesOrder(Integer id) {
        return restTemplate.postForObject("/sales-order/create/{id}", null, SalesOrder.class, id);
    }

    public SalesOrder addOrderToSalesOrder(SalesOrder salesOrder, Integer offerId) {
        return restTemplate.postForObject("/sales-order/add-order/{offerId}", salesOrder, SalesOrder.class, offerId);
    }

    public SalesOrder deleteOrderFromSalesOrder(SalesOrder salesOrder, Integer orderId) {
        return restTemplate.postForObject("/sales-order/delete-order/{orderId}", salesOrder, SalesOrder.class, orderId);
    }

    public SalesOrder saveSalesOrder(SalesOrder salesOrder) {
        return restTemplate.postForObject("/save-sales-order", salesOrder, SalesOrder.class);
    }

    public void deleteStudent(Integer studentId) {
        restTemplate.delete("/student/delete/{studentId}", studentId);
    }

    public void updateStudent(Integer studentId, Student student) {
        restTemplate.put("/student/{studentId}",student, studentId);
    }
}
