package com.srm.pages;

import com.srm.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h3")
    WebElement productName;

    @FindBy(xpath = "//p[contains(text(),'₹') or contains(text(),'$')]")
    WebElement productPrice;

    @FindBy(tagName = "p")
    WebElement productDescription;

    @FindBy(xpath = "//button[contains(text(),'Add to Basket')]")
    WebElement addToBasketBtn;

    public String getProductName() {
        return getText(productName);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public String getProductDescription() {
        return getText(productDescription);
    }

    public boolean isAddToBasketVisible() {
        try {
            return addToBasketBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}