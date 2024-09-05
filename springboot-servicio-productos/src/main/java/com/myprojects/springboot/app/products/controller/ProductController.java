package com.myprojects.springboot.app.products.controller;

import com.myprojects.springboot.app.products.models.entity.Product;
import com.myprojects.springboot.app.products.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/v1/list")
    public List<Product> findAll() {
        return iProductService.findAll();
    }

    @GetMapping("/v1/list/{id}")
    public Product getProductById(@PathVariable Long id) throws InterruptedException {
        if (id.equals(10L)) {
            throw new IllegalStateException("Producto no encontrado");
        }
        if (id.equals(7L)) {
            TimeUnit.SECONDS.sleep(5L);
        }
        return iProductService.findById(id);
    }
}
