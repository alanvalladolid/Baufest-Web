package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage
{
    public WebDriver driver;
    public WebDriverWait wait;

    //Constructor
    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 60);
    }

    //Functions
    public WebElement findElement(By locator)
    {
        return driver.findElement(locator);
    }

    public void click(By locator)
    {
        driver.findElement(locator).click();
    }

    public void write(String inputText, By locator)
    {
        driver.findElement(locator).sendKeys(inputText);
    }

    public String getText(By locator)
    {
        return driver.findElement(locator).getText();
    }

    public Boolean isDisplayed(By locator)
    {
        try
        {
            return driver.findElement(locator).isDisplayed();
        }
        catch (NoSuchElementException Ex)
        {
            return false;
        }
    }

    public boolean waitForElement(By locator)
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        }
        catch (TimeoutException Ex)
        {
            return false;
        }
    }

    public boolean waitForAlert()
    {
        try
        {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        }
        catch (TimeoutException Ex)
        {
            return false;
        }
    }

    public void selectMenuOption(String MenuOption)
    {
        waitForElement(By.xpath("//a[contains(text(),'"+ MenuOption +"')]")) ;
        driver.findElement(By.xpath("//a[contains(text(),'"+ MenuOption +"')]")).click();
    }

    public void clickOKAlert()
    {
        driver.switchTo().alert().accept();
    }
}
