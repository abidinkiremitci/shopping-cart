package com.tr.trendyol;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class Campaign extends Discount
{
    Category category;

    int minQuantity;

    public Campaign()
    {
        super();
    }

    public Campaign(Category category, Double amountOrRate, Integer minQuantity, DiscountType discountType)
    {
        super(discountType,amountOrRate);
        this.category = category;
        this.minQuantity = minQuantity;
    }
}
