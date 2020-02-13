package com.tr.ecommerce;

public class DeliveryCostCalculator
{
    Double costPerDelivery;

    Double costPerProduct;

    Double fixedCost;

    public DeliveryCostCalculator(Double costPerDelivery, Double costPerProduct, Double fixedCost)
    {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    public Double calculateFor(ShoppingCart cart)
    {
        // (costPerDelivery * NumberOfDeliveries) + (CostPerProduct * NumberOfProducts)
        return (costPerDelivery * cart.getNoOfDeliveries()) + (costPerProduct * cart.itemList.size());
    }
}
