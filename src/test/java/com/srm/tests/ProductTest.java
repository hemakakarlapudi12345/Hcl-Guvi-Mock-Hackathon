package com.srm.tests;

import com.srm.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.srm.pages.SweetsPage;

public class ProductTest extends BaseTest {

    @Test
    public void testProductModule() {
        SweetsPage sp = new SweetsPage(driver);
        sp.openSweets();
        int productCount = sp.getProductCount();
        System.out.println("Product count: " + productCount);
        Assert.assertTrue(productCount > 0,
                "No products displayed on sweets page");
        int addBtnCount = sp.getAddButtonCount();
        System.out.println("Add button count: " + addBtnCount);
        Assert.assertTrue(addBtnCount > 0,
                "Add to Basket buttons not visible");
        sp.openSweets();
        int newCount = sp.getProductCount();
        System.out.println("Product count after reload: " + newCount);
        Assert.assertTrue(newCount > 0,
                "Products not displayed after navigation");
    }
}