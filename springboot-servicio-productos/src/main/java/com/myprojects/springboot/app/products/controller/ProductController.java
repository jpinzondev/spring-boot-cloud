package com.myprojects.springboot.app.products.controller;

import com.myprojects.springboot.app.products.models.entity.Product;
import com.myprojects.springboot.app.products.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/v1/list")
    public List<Product> findAll() {
        return iProductService.findAll();
    }

    @GetMapping("/v1/list/{id}")
    public Product getProductById(@PathVariable Long id) {
        return iProductService.findById(id);
    }
}
