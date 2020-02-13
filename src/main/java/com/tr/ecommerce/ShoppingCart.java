package com.tr.ecommerce;

import lombok.Setter;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ShoppingCart
{
    static DecimalFormat decimalFormat = new DecimalFormat("##.00");

    @Setter
    DeliveryCostCalculator deliveryCostCalculator;

    Set<Item> itemList = new HashSet<>();

    Set<Coupon> couponList = new HashSet<>();

    public ShoppingCart()
    {
    }

    public ShoppingCart(DeliveryCostCalculator deliveryCostCalculator)
    {
        this.deliveryCostCalculator = deliveryCostCalculator;
    }

    public ShoppingCart(DeliveryCostCalculator deliveryCostCalculator, Set<Item> itemList, Set<Coupon> couponList)
    {
        this.deliveryCostCalculator = deliveryCostCalculator;
        this.itemList = itemList;
        this.couponList = couponList;
    }

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
        if (coupon != null)
        {
            couponList.add(coupon);
        }
    }

    public void applyCampaing(Campaign campaign)
    {
        if (campaign != null)
        {
            itemList.forEach(item -> item.addCampaign(campaign));
        }
    }

    public double getTotalAmount()
    {
        double totalAmount = 0;
        for (Item item : itemList)
        {
            totalAmount += item.getQuantity() * item.getProduct().getPrice();
        }
        return rountTwoDigit(totalAmount);
    }

    public double getCouponDiscount()
    {
        double couponDiscountAmount = 0;
        double subTotal = getTotalAmount() - getCampaignDiscount();
        for (Coupon coupon : couponList)
        {
            if (coupon.getMinPurchaseAmount() < subTotal)
            {
                double couponDiscount = coupon.getDiscount(subTotal);
                couponDiscountAmount = couponDiscount > couponDiscountAmount ? couponDiscount : couponDiscountAmount;
            }
        }
        return rountTwoDigit(couponDiscountAmount);
    }

    public double getCampaignDiscount()
    {
        double campaignDiscountAmount = 0;
        for (Item item : itemList)
        {
            campaignDiscountAmount += item.calculateDiscount();
        }
        return rountTwoDigit(campaignDiscountAmount);
    }

    public double getTotalAmountAfterDiscount()
    {
        return rountTwoDigit(getTotalAmount() - getCampaignDiscount() - getCouponDiscount());
    }

    public double getDeliveryCost()
    {
        return deliveryCostCalculator.calculateFor(this);
    }

    public int getNoOfDeliveries()
    {
        Set<Category> categories = new HashSet<>();
        itemList.stream().forEach(item -> categories.add(item.product.category));
        return categories.size();
    }

    double rountTwoDigit(double input)
    {
        return (double) Math.round(input * 100) / 100;
    }
}
