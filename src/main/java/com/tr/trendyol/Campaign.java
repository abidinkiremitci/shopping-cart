package com.tr.trendyol;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Getter
public class Campaign
{
    UUID id;

    Category category;

    DiscountType discountType;

    Double amount;

    Integer discountRate;

    public Campaign()
    {
        this.id = UUID.randomUUID();
    }

    public Campaign(Category category, Double amount, Integer discountRate, DiscountType discountType)
    {
        this.id = UUID.randomUUID();
        this.category = category;
        this.amount = amount;
        this.discountRate = discountRate;
        this.discountType = discountType;
    }
}
