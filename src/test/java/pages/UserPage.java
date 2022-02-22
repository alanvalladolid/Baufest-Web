package pages;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.BasePage;

public class UserPage extends BasePage
{
    //Constructor
    public UserPage(WebDriver driver)
    {
        super(driver);
    }

    //Web Elements Locators
    By userWelcomeText = By.id("nameofuser");
    By logOutButton = By.id("logout2");
    By categoriesOpt = By.id("cat");
    By products = By.cssSelector("[class='card h-100']");
    By addToCartButton = By.cssSelector("[onclick*='addToCart'][class='btn btn-success btn-lg']");

    public void clickLogOut()
    {
        waitForElement(logOutButton);
        click(logOutButton);
    }

    public boolean isWelcomeTextDisplayed(String Username)
    {
        waitForElement(userWelcomeText);
        String UserWelcome = getText(userWelcomeText);

        Assert.assertTrue(UserWelcome.contains("Welcome " + Username));
        return true;
    }

    public void selectCategoriesOption(String CategoriesOption)
    {
        //waitForElement(categoriesOpt);
        //driver.findElement(By.xpath("//a[contains(text(),'"+ CategoriesOption +"')]")).click();

        Boolean staleElement = true;

        while(staleElement)
        {
            try
            {
                WebElement category = driver.findElement(By.xpath("//a[contains(text(),'"+ CategoriesOption +"')]"));
                wait.until(ExpectedConditions.elementToBeClickable(category));
                category.click();
                staleElement = false;
            }
            catch(StaleElementReferenceException Ex)
            {
                staleElement = true;
            }
        }
    }

    public void selectProduct(String ProductName)
    {
        waitForElement(products);
        driver.findElement(By.xpath("//a[contains(text(),'"+ ProductName +"')]")).click();
    }

    public void clickAddToCart()
    {
        waitForElement(addToCartButton);
        click(addToCartButton);
    }

    public Boolean isAlertProductAddedDisplayed()
    {
        try
        {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            String alertMessage = driver.switchTo().alert().getText();
            Assert.assertTrue(alertMessage.contains("Product added"));
            return true;
        }
        catch (AssertionError Ex)
        {
            return false;
        }
    }

}
