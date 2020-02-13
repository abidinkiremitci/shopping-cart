package com.tr.ecommerce;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = "product")
@Getter
public class Item
{
    Product product;

    Integer quantity;

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

    public double calculateDiscount()
    {
        double productPrice = quantity * product.getPrice();
        double discountAmount = 0;
        for (Campaign campaign : product.getCategory().getCampaignList())
        {
            if(quantity >= campaign.getMinQuantity())
            {
                double discount = campaign.getDiscount(productPrice);
                discountAmount = discountAmount > discount ? discountAmount : discount;
            }
        }
        return discountAmount;
    }

    public void addCampaign(Campaign campaign) {
        product.addCampaing(campaign);
    }
}
