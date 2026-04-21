package com.srm.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.srm.base.BasePage;

public class SweetsPage extends BasePage {

    public SweetsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //  Navigation
    @FindBy(linkText = "Sweets")
    WebElement sweetsLink;

    //  Product cards
    @FindBy(css = ".card")
    List<WebElement> cards;

    //  Add to Basket buttons inside cards
    @FindBy(css = ".addItem")
    List<WebElement> addButtons;

    //  Basket navigation (with count)
    @FindBy(xpath = "//a[contains(@class,'nav-link') and contains(.,'Basket')]")
    WebElement basketNav;

    // ================= METHODS =================

    public void openSweets() {
        click(sweetsLink);
    }

    public int getProductCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cards));
        return cards.size();
    }

    public void addFirstProduct() {

        wait.until(driver -> addButtons.size() > 0);

        System.out.println("Buttons found: " + addButtons.size());

        addButtons.get(0).click();
    }

    public void addMultipleProducts(int count) {

        wait.until(driver -> addButtons.size() > 0);

        for (int i = 0; i < count && i < addButtons.size(); i++) {
            addButtons.get(i).click();
        }
    }
    public int getAddButtonCount() {
        wait.until(driver -> addButtons.size() > 0);
        return addButtons.size();
    }
    public String getBasketCountText() {
        return basketNav.getText(); // e.g. Basket (2)
    }
}