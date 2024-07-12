package com.myprojects.springboot.app.item.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Product product;
    private Integer count;

    public Double getTotal() {
        return product.getPrice() * count.doubleValue();
    }
}
