package com.myprojects.springboot.app.item.service;

import com.myprojects.springboot.app.item.client.ProductClientFeign;
import com.myprojects.springboot.app.item.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductClientFeign productClientFeign;

    @Override
    public List<Item> findAll() {
        return productClientFeign.findAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findBy(Long id, Integer count) {
        return new Item(productClientFeign.findDetail(id), count);
    }
}
