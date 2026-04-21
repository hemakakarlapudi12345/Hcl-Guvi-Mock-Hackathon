package com.srm.tests;

import com.srm.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.srm.pages.SweetsPage;
import com.srm.pages.BasketPage;

public class BasketTest extends BaseTest {

    @Test
    public void testBasketFlow() {
        SweetsPage sp = new SweetsPage(driver);
        BasketPage bp = new BasketPage(driver);
        sp.openSweets();
        sp.addMultipleProducts(2);
        String basketText = bp.getBasketText();
        System.out.println("Basket text: " + basketText);
        Assert.assertTrue(basketText.contains("2"),
                "Basket count not updated");
        bp.openBasket();
        int items = bp.getItemCount();
        System.out.println("Items in basket: " + items);
        Assert.assertEquals(items, 2, "Items not added correctly");
        String total = bp.getTotalPrice();
        System.out.println("Total price: " + total);
        Assert.assertTrue(total.length() > 0, "Total price missing");
        bp.removeAllItems();
        Assert.assertTrue(bp.isBasketEmpty(),
                "Basket not empty after removing items");
    }
}