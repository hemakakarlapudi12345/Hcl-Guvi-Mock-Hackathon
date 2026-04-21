package com.srm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.srm.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //  Login navigation
    @FindBy(xpath = "//a[text()='Login']")
    WebElement loginLink;

    //  CORRECT INPUT FIELDS (updated)
    @FindBy(id = "exampleInputEmail")
    WebElement emailField;

    @FindBy(id = "exampleInputPassword")
    WebElement passwordField;

    @FindBy(id = "btn_login")
    WebElement loginBtn;

    //  Error message
    @FindBy(xpath = "//*[contains(@class,'invalid-email')]")
    WebElement errorMsg;

    //  Something visible after login (adjust if needed)
    @FindBy(tagName = "h2")
    WebElement userSection;

    // ================= ACTIONS =================

    public void openLogin() {
        click(loginLink);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    // ================= VALIDATIONS =================

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMsg));
            return errorMsg.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isLoginSuccessful() {
        try {
            return !loginBtn.isDisplayed(); 
        } catch (Exception e) {
            return true;
        }
    }
}