package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.BasePage;

public class SignUpPage extends BasePage
{
    //Constructor
    public SignUpPage(WebDriver driver)
    {
        super(driver);
    }

    //Web Elements Locators
    By userNameText = By.id("sign-username");
    By passwordText = By.id("sign-password");
    By signUpButton = By.cssSelector("[type='button'][onclick='register()']");

    //Functions
    public void enterUsername(String Username)
    {
        waitForElement(userNameText);
        click(userNameText);
        write(Username, userNameText);
    }

    public void enterPassword(String Password)
    {
        waitForElement(passwordText);
        click(passwordText);
        write(Password, passwordText);
    }

    public void clickSignUp()
    {
        waitForElement(signUpButton);
        click(signUpButton);
    }

    public Boolean isAlertSignUpSuccessfulDisplayed()
    {
        try
        {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            String alertMessage = driver.switchTo().alert().getText();
            Assert.assertTrue(alertMessage.contains("Sign up successful"));
            return true;
        }
        catch (AssertionError Ex)
        {
            return false;
        }
    }

}
