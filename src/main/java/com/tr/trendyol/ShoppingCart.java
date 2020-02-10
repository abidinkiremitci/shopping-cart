package com.tr.trendyol;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ShoppingCart
{
    Set<Item> itemList = new HashSet<>();

    Set<Coupon> couponList;

    double discountAmount;

    double totalAmount;

    public void addItem(final Product product, Integer quantity)
    {
        Optional<Item> alreadyExistsItem = itemList.stream().filter(item -> item.product.equals(product)).findAny();
        if (alreadyExistsItem.isPresent())
        {
            alreadyExistsItem.get().addQuantity(quantity);
        }
        else
        {
            itemList.add(new Item(product, quantity));
        }
    }

    public void updateItem(final Product product, Integer quantity)
    {
        Optional<Item> alreadyExistsItem = itemList.stream().filter(item -> item.product.equals(product)).findAny();
        if (alreadyExistsItem.isPresent())
        {
            alreadyExistsItem.get().updateQuantity(quantity);
        }
        else
        {
            itemList.add(new Item(product, quantity));
        }
    }

    public void applyCoupon(Coupon coupon)
    {
        if(couponList==null)
        {
            couponList = new HashSet<>();
        }
        couponList.add(coupon);
    }

    public double getTotalAmountAfterDiscount()
    {
        return 0;
    }

    public double getCouponDiscount()
    {
        return 0;
    }

    public double getCampaignDiscount()
    {
        return 0;
    }

    public double getDeliveryCost()
    {
        return 0;
    }

    public int getNoOfDeliveries()
    {
        Set<Category> categories = new HashSet<>();
        itemList.stream().forEach(item -> categories.add(item.product.category));
        return categories.size();
    }

    private void applyDiscounts()
    {

    }

    private void applyCoupons()
    {

    }
}
