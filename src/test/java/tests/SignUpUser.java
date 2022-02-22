package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;
import utilities.BaseTest;
import java.awt.*;
import java.io.IOException;

public class SignUpUser extends BaseTest
{
    @BeforeMethod
    public void setUpTest() throws IOException
    {
        getProperties();
        getExtentObj();
        openChrome();
    }

    @Test(retryAnalyzer =  utilities.RetryTest.class)
    public void signUp() throws IOException, AWTException
    {
        try
        {
            extentTest = extent.createTest("Sign Up");

            HomePage home = new HomePage(driver);
            home.selectMenuOption("Sign up");

            SignUpPage signUp = new SignUpPage(driver);
            signUp.enterUsername(prop.getProperty("username"));
            signUp.enterPassword(prop.getProperty("password"));
            signUp.clickSignUp();

            if(signUp.isAlertSignUpSuccessfulDisplayed())
            {
                screenFile = screenShotAlert("SignUp");
                extentTest.pass("Passed").addScreenCaptureFromPath(screenFile);
                signUp.clickOKAlert();
            }
            else
            {
                screenFile = screenShotAlert("SignUp");
                extentTest.fail("Failed").addScreenCaptureFromPath(screenFile);
                signUp.clickOKAlert();
            }
        }

        catch (Exception ex)
        {
            screenFile = screenShotAlert("SignUp");
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
