package com.myprojects.springboot.app.item.service;

import com.myprojects.springboot.app.item.models.Item;

import java.util.List;

public interface ItemService {

    public List<Item> findAll();
    public Item findBy(Long id, Integer count);
}
