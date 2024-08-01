package com.myprojects.springboot.app.item.client;

import com.myprojects.springboot.app.item.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="service-products", url = "localhost:8001")
public interface ProductClientFeign {

    @GetMapping("/v1/list")
    List<Product> findAll();

    @GetMapping("/v1/list/{id}")
    Product findDetail(@PathVariable Long id);
}
