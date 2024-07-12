package com.myprojects.springboot.app.item.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private double price;
    private Date createAt;
}
