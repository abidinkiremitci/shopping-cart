package com.tr.trendyol;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class Coupon extends Discount
{
    Double minPurchaseAmount;

    public Coupon()
    {
        super();
    }

    public Coupon(Double minPurchaseAmount, int discountRate, DiscountType discountType)
    {
        super(discountType, discountRate);
        this.minPurchaseAmount = minPurchaseAmount;
    }
}
