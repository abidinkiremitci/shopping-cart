package com.tr.trendyol;

import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
public class Product
{
    UUID id;

    String title;

    BigDecimal price;

    Category category;

    public Product()
    {
        id = UUID.randomUUID();
    }

    public Product(String title, BigDecimal price, Category category)
    {
        id = UUID.randomUUID();
        this.title = title;
        this.price = price;
        this.category = category;
    }
}
