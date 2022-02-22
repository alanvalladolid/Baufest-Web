package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePage;

public class HomePage extends BasePage
{
    //Constructor
    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    //Web Elements Locators
    By logInButton = By.id("login2");

    //Functions
    public boolean isLogInDisplayed()
    {
        waitForElement(logInButton);
        return isDisplayed(logInButton);
    }

}
