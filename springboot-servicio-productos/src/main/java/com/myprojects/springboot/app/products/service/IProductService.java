package com.myprojects.springboot.app.products.service;

import com.myprojects.springboot.app.products.models.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll();
    Product findById(Long id);
}
