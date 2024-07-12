package com.myprojects.springboot.app.item.controller;

import com.myprojects.springboot.app.item.models.Item;
import com.myprojects.springboot.app.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/v1/list")
    public List<Item> list() {
        return itemService.findAll();
    }
    @GetMapping("/v1/{id}/count/{count}")
    public Item detail(@PathVariable Long id, @PathVariable Integer count) {
        return itemService.findBy(id, count);
    }
}
