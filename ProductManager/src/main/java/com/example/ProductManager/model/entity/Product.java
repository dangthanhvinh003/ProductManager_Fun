package com.example.ProductManager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private int idProduct;
    private String nameProduct;
    private int priceProduct;
    private TypeProduct typeProduct;
}
