package com.srm.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.srm.base.BaseTest;
import com.srm.pages.LoginPage;

public class LoginTest extends BaseTest {
    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, String type) {
        LoginPage lp = new LoginPage(driver);
        lp.openLogin();
        lp.login(email, password);
        if (type.equalsIgnoreCase("valid")) {
            boolean isSuccess = lp.isLoginSuccessful();
            System.out.println("Login success status: " + isSuccess);
            Assert.assertTrue(isSuccess, "Valid login failed for: " + email);
        } else {
            String error = lp.getErrorMessage();
            System.out.println("Error message: " + error);
            Assert.assertTrue(error.contains("demo email"),
                    "Error message not displayed for invalid login");
        }
    }
    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"oneorder@sweetshop.local", "qwerty", "valid"},
                {"twoorders@sweetshop.local", "qwerty", "valid"},
                {"fiveorders@sweetshop.local", "qwerty", "valid"},
                {"wrongemail@example.com", "testing", "invalid"}
        };
    }
}