package com.tr.trendyol;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Getter
public abstract class Discount
{
    UUID id;

    DiscountType discountType;

    double amountOrRate;

    public Discount()
    {
        this.id = UUID.randomUUID();
    }

    public Discount(DiscountType discountType, double amountOrRate)
    {
        this.id = UUID.randomUUID();
        this.discountType = discountType;
        this.amountOrRate = amountOrRate;
    }

    public double getDiscount(double amount)
    {
        switch (this.discountType)
        {
            case RATE:
                return amount * amountOrRate / 100;
            case AMOUNT:
                return amountOrRate;
            default:
                throw new IllegalStateException("Unexpected value: " + this.discountType);
        }
    }
}
