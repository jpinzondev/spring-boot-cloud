package com.myprojects.springboot.app.item.controller;

import com.myprojects.springboot.app.item.models.Item;
import com.myprojects.springboot.app.item.models.Product;
import com.myprojects.springboot.app.item.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private ItemService itemService;

    @Value("${configuration.text}")
    private String text;

    @Value("${server.port}")
    private String port;

    @GetMapping("/v1/list")
    public List<Item> list(
            @RequestParam(name="name", required = false) String name,
            @RequestHeader(name="token-request") String token) {
        System.out.println("name " + name);
        System.out.println("token " + token);
        return itemService.findAll();
    }

    @GetMapping("/v1/{id}/count/{count}")
    public Item detail(@PathVariable Long id, @PathVariable Integer count) {
        return circuitBreakerFactory.create("items")
                .run(
                    () -> itemService.findBy(id, count),
                    e -> alternativeMethod(id, count, e)
                );
    }

    @CircuitBreaker(name="items", fallbackMethod = "alternativeMethod")
    @GetMapping("/v1/ver2/{id}/count/{count}")
    public Item detail2(@PathVariable Long id, @PathVariable Integer count) {
        return itemService.findBy(id, count);
    }

    @CircuitBreaker(name="items", fallbackMethod = "alternativeMethodTwo")
    @TimeLimiter(name="items")
    @GetMapping("/v1/ver3/{id}/count/{count}")
    public CompletableFuture<Item> detail3(@PathVariable Long id, @PathVariable Integer count) {
        return CompletableFuture.supplyAsync(() -> itemService.findBy(id, count));
    }

    public Item alternativeMethod(Long id, Integer count, Throwable e) {
        Item item = new Item();
        Product product = new Product();
        item.setCount(count);
        product.setId(id);
        product.setName("Camara Sony");
        product.setPrice(500.00);
        item.setProduct(product);
        logger.error(e.toString());
        return item;
    }

    public CompletableFuture<Item> alternativeMethodTwo(Long id, Integer count, Throwable e) {
        Item item = new Item();
        Product product = new Product();
        item.setCount(count);
        product.setId(id);
        product.setName("Camara Sony");
        product.setPrice(500.00);
        item.setProduct(product);
        logger.error(e.toString());
        return CompletableFuture.supplyAsync(() ->item);
    }

    @GetMapping("/get-config")
    public ResponseEntity<?> getConfig() {
        logger.info(text);
        Map<String, String> map = new HashMap<>();
        map.put("text", text);
        map.put("port", port);
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }
}
