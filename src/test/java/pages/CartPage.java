package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.BasePage;

public class CartPage extends BasePage
{
    //Constructor
    public CartPage(WebDriver driver)
    {
        super(driver);
    }

    //Web Elements Locators
    By productsText = By.xpath("//h2[contains(text(),'Products')]");
    By productList = By.xpath("//*[@id='tbodyid']/tr/td[2]");

    //Functions
    public boolean isProductDisplayed(String ProductName)
    {
        waitForElement(productsText);
        waitForElement(productList);
        String Product = getText(productList);

        Assert.assertTrue(Product.contains(ProductName));
        return true;
    }

}
