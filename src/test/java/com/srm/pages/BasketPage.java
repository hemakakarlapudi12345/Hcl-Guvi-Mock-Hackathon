package com.srm.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.srm.base.BasePage;

public class BasketPage extends BasePage {

    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //  Open basket
    @FindBy(xpath = "//a[contains(@class,'nav-link') and contains(.,'Basket')]")
    WebElement basketLink;
    //  Basket items
    @FindBy(xpath = "//ul[@id='basketItems']/li")
    List<WebElement> items;

    //  Delete item links
    @FindBy(xpath = "//a[contains(text(),'Delete Item')]")
    List<WebElement> deleteButtons;

    //  Total price
    @FindBy(xpath = "//li[contains(.,'Total')]//strong")
    WebElement totalPrice;

    // ================= METHODS =================

    public void openBasket() {
        click(basketLink);
    }

    public String getBasketText() {
        return basketLink.getText();
    }

    public int getItemCount() {
        return items.size() - 1; 
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void removeAllItems() {

        while (deleteButtons.size() > 0) {

            click(deleteButtons.get(0));

            
            driver.switchTo().alert().accept();
        }
    }

    public boolean isBasketEmpty() {
        return getItemCount() == 0;
    }
}