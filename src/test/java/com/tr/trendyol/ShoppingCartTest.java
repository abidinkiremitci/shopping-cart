package com.tr.trendyol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShoppingCartTest
{
    ShoppingCart shoppingCart;
    DeliveryCostCalculator deliveryCostCalculator;

    Category foods;
    Product apple;
    Product peach;
    Product tomato;
    Campaign foodsCampaign5R;
    Campaign foodsCampaign10R;
    Campaign foodsCampaign7A;

    Category clothes;
    Product sock;
    Product shirt;
    Campaign clothesCampaign5R;
    Campaign clothesCampaign15R;
    Campaign clothesCampaign12A;

    Category electronics;
    Product memory;
    Product camera;
    Campaign electronicsCampaign10R;
    Campaign electronicsCampaign20R;
    Campaign electronicsCampaign15A;

    Coupon coupon10;
    Coupon coupon50;
    Coupon coupon100;

    @BeforeEach
    public void beforeEach()
    {
        deliveryCostCalculator = new DeliveryCostCalculator(3.0, 2.0, 2.99);
        shoppingCart = new ShoppingCart(deliveryCostCalculator);

        foods = new Category("foods");
        foodsCampaign5R = new Campaign(foods, 5.0, 10, DiscountType.RATE);
        foodsCampaign10R = new Campaign(foods, 10.0, 20, DiscountType.RATE);
        foodsCampaign7A = new Campaign(foods, 7.0, 10, DiscountType.AMOUNT);

        apple = new Product("apple", 2.0, foods);
        peach = new Product("peach", 4.0, foods);
        tomato = new Product("tomato", 1.0, foods);

        clothes = new Category("clothes");
        clothesCampaign5R = new Campaign(clothes, 5.0, 3, DiscountType.RATE);
        clothesCampaign15R = new Campaign(clothes, 15.0, 5, DiscountType.RATE);
        clothesCampaign12A = new Campaign(clothes, 12.0, 4, DiscountType.AMOUNT);
        sock = new Product("sock", 1.0, clothes);
        shirt = new Product("shirt", 1.0, clothes);

        electronics = new Category("electronics");
        electronicsCampaign10R = new Campaign(electronics, 10.0, 3, DiscountType.RATE);
        electronicsCampaign20R = new Campaign(electronics, 20.0, 5, DiscountType.RATE);
        electronicsCampaign15A = new Campaign(electronics, 15.0, 4, DiscountType.AMOUNT);
        memory = new Product("memory", 100.0, clothes);
        camera = new Product("camera", 200.0, clothes);

        coupon10 = new Coupon(10.0, 2, DiscountType.RATE);
        coupon50 = new Coupon(50.0, 4, DiscountType.RATE);
        coupon100 = new Coupon(100.0, 10, DiscountType.RATE);
    }

    @Test
    public void testEmptyCart()
    {
        assertEquals(0, shoppingCart.itemList.size());
        assertEquals(0, shoppingCart.couponList.size());
        assertEquals(0, shoppingCart.getCampaignDiscount());
        assertEquals(0, shoppingCart.getCouponDiscount());
        assertEquals(0, shoppingCart.getDeliveryCost());
        assertEquals(0, shoppingCart.getTotalAmount());
        assertEquals(0, shoppingCart.getTotalAmountAfterDiscount());
    }

    @Test
    public void testOneItem()
    {
        shoppingCart.addItem(apple, 5);

        assertEquals(1, shoppingCart.itemList.size());
        assertEquals(0, shoppingCart.couponList.size());
        assertEquals(10.0, shoppingCart.getTotalAmount());
        assertEquals(0, shoppingCart.getCampaignDiscount());
        assertEquals(0, shoppingCart.getCouponDiscount());
        assertEquals(10.0, shoppingCart.getTotalAmountAfterDiscount());
        assertEquals(5.0, shoppingCart.getDeliveryCost());
    }

    @Test
    public void testOneItemWithCampaign()
    {
        shoppingCart.addItem(apple, 12);

        shoppingCart.applyCampaing(foodsCampaign5R);
        shoppingCart.applyCampaing(foodsCampaign10R);
        shoppingCart.applyCampaing(foodsCampaign7A);

        assertEquals(1, shoppingCart.itemList.size());
        assertEquals(0, shoppingCart.couponList.size());
        assertEquals(24.0, shoppingCart.getTotalAmount());
        assertEquals(7.0, shoppingCart.getCampaignDiscount());
        assertEquals(0, shoppingCart.getCouponDiscount());
        assertEquals(17.0, shoppingCart.getTotalAmountAfterDiscount());
        assertEquals(5.0, shoppingCart.getDeliveryCost());
    }

    @Test
    public void testOneItemWithCoupon()
    {
        shoppingCart.addItem(memory, 12);

        shoppingCart.applyCoupon(coupon10);
        shoppingCart.applyCoupon(coupon50);
        shoppingCart.applyCoupon(coupon100);

        assertEquals(1, shoppingCart.itemList.size());
        assertEquals(3, shoppingCart.couponList.size());
        assertEquals(1200.0, shoppingCart.getTotalAmount());
        assertEquals(0.0, shoppingCart.getCampaignDiscount());
        assertEquals(120, shoppingCart.getCouponDiscount());
        assertEquals(1080.0, shoppingCart.getTotalAmountAfterDiscount());
        assertEquals(5.0, shoppingCart.getDeliveryCost());
    }

    @Test
    public void testAllFeature()
    {
        shoppingCart.addItem(apple, 5);
        shoppingCart.addItem(peach, 3);
        shoppingCart.addItem(tomato, 4);
        shoppingCart.addItem(sock, 5);
        shoppingCart.addItem(shirt, 3);
        shoppingCart.addItem(memory, 1);
        shoppingCart.addItem(camera, 4);

        shoppingCart.applyCoupon(coupon10);
        shoppingCart.applyCoupon(coupon50);
        shoppingCart.applyCoupon(coupon100);

        shoppingCart.applyCampaing(foodsCampaign5R);
        shoppingCart.applyCampaing(foodsCampaign10R);
        shoppingCart.applyCampaing(foodsCampaign7A);

        shoppingCart.applyCampaing(clothesCampaign5R);
        shoppingCart.applyCampaing(clothesCampaign15R);
        shoppingCart.applyCampaing(clothesCampaign12A);

        shoppingCart.applyCampaing(electronicsCampaign10R);
        shoppingCart.applyCampaing(electronicsCampaign20R);
        shoppingCart.applyCampaing(electronicsCampaign15A);


        assertEquals(7, shoppingCart.itemList.size());
        assertEquals(3, shoppingCart.couponList.size());
        assertEquals(934.0, shoppingCart.getTotalAmount());
        assertEquals(52.15, shoppingCart.getCampaignDiscount());
        assertEquals(88.19, shoppingCart.getCouponDiscount());
        assertEquals(793.66, shoppingCart.getTotalAmountAfterDiscount());
        assertEquals(20.0, shoppingCart.getDeliveryCost());
    }
}
