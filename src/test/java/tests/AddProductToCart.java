package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LogInPage;
import pages.UserPage;
import utilities.BaseTest;

import java.awt.*;
import java.io.IOException;

public class AddProductToCart extends BaseTest
{
    @BeforeMethod
    public void setUpTest() throws IOException
    {
        getProperties();
        getExtentObj();
        openChrome();
    }

    @Test(retryAnalyzer =  utilities.RetryTest.class)
    public void AddToCart() throws IOException, AWTException
    {
        try
        {
            extentTest = extent.createTest("Add Product To Cart");

            //LogIn
            HomePage home = new HomePage(driver);
            home.selectMenuOption("Log in");

            LogInPage logIn = new LogInPage(driver);
            logIn.enterUsername(prop.getProperty("username"));
            logIn.enterPassword(prop.getProperty("password"));
            logIn.clickLogIn();

            //Add To Cart
            UserPage user = new UserPage(driver);
            user.selectCategoriesOption("Laptops");
            user.selectProduct(prop.getProperty("productName"));
            user.clickAddToCart();

            if(user.isAlertProductAddedDisplayed())
            {
                screenFile = screenShotAlert("AddToCart");
                extentStep = extentTest.createNode("Add To Cart");
                extentStep.pass("Passed").addScreenCaptureFromPath(screenFile);
                user.clickOKAlert();
            }
            else
            {
                screenFile = screenShot("AddToCart");
                extentStep = extentTest.createNode("Add To Cart");
                extentStep.fail("Failed").addScreenCaptureFromPath(screenFile);
                user.clickOKAlert();
            }

            //Product in Cart
            user.selectMenuOption("Cart");

            CartPage cart = new CartPage(driver);
            if(cart.isProductDisplayed(prop.getProperty("productName")))
            {
                screenFile = screenShot("Cart");
                extentStep = extentTest.createNode("Product in Cart");
                extentStep.pass("Passed").addScreenCaptureFromPath(screenFile);
            }
            else
            {
                screenFile = screenShot("Cart");
                extentStep = extentTest.createNode("Product in Cart");
                extentStep.fail("Failed").addScreenCaptureFromPath(screenFile);
            }

            //LogOut
            user.selectMenuOption("Log out");
        }

        catch (Exception ex)
        {
            screenFile = screenShotAlert("ProductCart");
            extentTest.fail(ex.getMessage()).addScreenCaptureFromPath(screenFile);
            ex.printStackTrace();
            throw(ex);
        }
    }

    @AfterMethod
    public void closeTest()
    {
        closeChrome();
    }
}
