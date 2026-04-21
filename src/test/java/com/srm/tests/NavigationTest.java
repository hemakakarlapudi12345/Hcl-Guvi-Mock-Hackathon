package com.srm.tests;

import com.srm.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.srm.pages.NavigationPage;

public class NavigationTest extends BaseTest {

    @Test
    public void testNavigationModule() {

        NavigationPage np = new NavigationPage(driver);
        Assert.assertTrue(np.isHomeVisible(), "Home link missing");
        Assert.assertTrue(np.isSweetsVisible(), "Sweets link missing");
        Assert.assertTrue(np.isAboutVisible(), "About link missing");
        Assert.assertTrue(np.isLoginVisible(), "Login link missing");
        np.clickAbout();
        String heading = np.getAboutHeading();
        System.out.println("About Heading: " + heading);
        Assert.assertEquals(heading.trim(), "Sweet Shop Project",
                "Incorrect About page heading");
        Assert.assertTrue(np.isFooterPresent(), "Footer not present");
        String footerText = np.getFooterText();
        System.out.println("Footer: " + footerText);
        Assert.assertTrue(footerText.length() > 0,
                "Footer content missing");
    }
}