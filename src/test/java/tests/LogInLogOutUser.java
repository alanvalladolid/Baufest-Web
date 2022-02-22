package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.SignUpPage;
import pages.UserPage;
import utilities.BaseTest;

import java.awt.*;
import java.io.IOException;

public class LogInLogOutUser extends BaseTest
{
    @BeforeMethod
    public void setUpTest() throws IOException
    {
        getProperties();
        getExtentObj();
        openChrome();
    }

    @Test(retryAnalyzer =  utilities.RetryTest.class)
    public void logInLogOut() throws IOException, AWTException
    {
        try
        {
            extentTest = extent.createTest("LogIn LogOut");

            //LogIn
            HomePage home = new HomePage(driver);
            home.selectMenuOption("Log in");

            LogInPage logIn = new LogInPage(driver);
            logIn.enterUsername(prop.getProperty("username"));
            logIn.enterPassword(prop.getProperty("password"));
            logIn.clickLogIn();

            UserPage user = new UserPage(driver);
            if(user.isWelcomeTextDisplayed(prop.getProperty("username")))
            {
                screenFile = screenShot("LogIn");
                extentStep = extentTest.createNode("LogIn");
                extentStep.pass("Passed").addScreenCaptureFromPath(screenFile);
            }
            else
            {
                screenFile = screenShot("LogIn");
                extentStep = extentTest.createNode("LogIn");
                extentStep.fail("Failed").addScreenCaptureFromPath(screenFile);
            }

            //LogOut
            user.selectMenuOption("Log out");

            if(home.isLogInDisplayed())
            {
                screenFile = screenShot("LogOut");
                extentStep = extentTest.createNode("LogOut");
                extentStep.pass("Passed").addScreenCaptureFromPath(screenFile);
            }
            else
            {
                screenFile = screenShot("LogOut");
                extentStep = extentTest.createNode("LogOut");
                extentStep.fail("Failed").addScreenCaptureFromPath(screenFile);
            }
        }

        catch (Exception ex)
        {
            screenFile = screenShotAlert("LogInOut");
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
