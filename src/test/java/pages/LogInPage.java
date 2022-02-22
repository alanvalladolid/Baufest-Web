package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePage;

public class LogInPage extends BasePage
{
    //Constructor
    public LogInPage(WebDriver driver)
    {
        super(driver);
    }

    //Web Elements Locators
    By userNameText = By.id("loginusername");
    By passwordText = By.id("loginpassword");
    By logInButton = By.cssSelector("[type='button'][onclick='logIn()']");

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

    public void clickLogIn()
    {
        waitForElement(logInButton);
        click(logInButton);
    }
}
