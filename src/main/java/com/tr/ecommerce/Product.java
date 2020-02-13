package com.tr.ecommerce;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Getter
public class Product
{
    UUID id;

    String title;

    double price;

    Category category;

    public Product()
    {
        id = UUID.randomUUID();
    }

    public Product(String title, double price, Category category)
    {
        id = UUID.randomUUID();
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public void addCampaing(Campaign campaign) {
        if(campaign.getCategory().equals(category)) {
            category.addCampaign(campaign);
        }
    }
}
