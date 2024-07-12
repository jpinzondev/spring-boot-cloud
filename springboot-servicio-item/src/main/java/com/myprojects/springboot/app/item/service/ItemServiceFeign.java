package com.myprojects.springboot.app.item.service;

import com.myprojects.springboot.app.item.client.ProductClientRest;
import com.myprojects.springboot.app.item.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductClientRest productClientRest;

    @Override
    public List<Item> findAll() {
        return productClientRest.listAll().stream().map(p -> new Item(p, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findBy(Long id, Integer count) {
        return new Item(productClientRest.getProductById(id), count);
    }
}
