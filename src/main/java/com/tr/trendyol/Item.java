package com.tr.trendyol;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(of = "product")
@Getter
public class Item
{
    Product product;

    Integer quantity;

    @Setter
    Double discountAmount;

    public Item(Product product, Integer quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    public void addQuantity(Integer quantity) {
        this.quantity = this.quantity + quantity;
    }

    public void updateQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
