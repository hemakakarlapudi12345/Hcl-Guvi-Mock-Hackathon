package com.srm.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.srm.base.BasePage;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //  User name 
    @FindBy(id = "accountGreeting")
    WebElement userName;

    // Order history rows
    @FindBy(xpath = "//tbody[@id='transactionsBody']/tr")
    List<WebElement> orderRows;

    //  Order history section title
    @FindBy(xpath = "//h4[contains(text(),'Previous Orders')]")
    WebElement orderHistoryTitle;

    // ================= METHODS =================

    public String getUserName() {
        return getText(userName);
    }

    public boolean isOrderHistoryPresent() {
        return orderHistoryTitle.isDisplayed();
    }

    public int getOrderCount() {
        return orderRows.size();
    }
    public List<WebElement> getOrderRows() {
        return orderRows;
    }

    public void printOrders() {
        for (int i = 1; i < orderRows.size(); i++) {
            System.out.println(orderRows.get(i).getText());
        }
    }
}