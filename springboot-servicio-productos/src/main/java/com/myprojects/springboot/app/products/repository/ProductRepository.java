package com.myprojects.springboot.app.products.repository;

import com.myprojects.springboot.app.products.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
