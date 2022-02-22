package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest
{
    public WebDriver driver;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest extentTest;
    public ExtentTest extentStep;
    public String screenFile;
    public static String projectPath = System.getProperty("user.dir");
    public static Properties prop = new Properties();

    public static void getProperties() throws IOException
    {
        InputStream input = new FileInputStream(projectPath + "/src/test/java/utilities/DataTest.properties");
        prop.load(input);
    }

    public WebDriver openChrome()
    {
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("disable-notifications");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(ops);
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));

        return driver;
    }

    public void closeChrome()
    {
        driver.close();
        driver.quit();
        extent.flush();
    }

    public String screenShot(String filename) throws IOException
    {
        TakesScreenshot screenShotObj = ((TakesScreenshot)driver);
        File screenFileObj = screenShotObj.getScreenshotAs(OutputType.FILE);

        String destinationFile = projectPath + "/reports/screenshots/" + filename +".png";
        File destinationFileObj = new File(destinationFile);

        FileUtils.copyFile(screenFileObj, destinationFileObj);

        return "screenshots/" + destinationFileObj.getName();
    }

    public String screenShotAlert(String filename) throws AWTException, IOException
    {
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        String destinationFile = projectPath + "/reports/screenshots/" + filename +".png";
        File destinationFileObj = new File(destinationFile);
        ImageIO.write(image, "png", destinationFileObj);
        return "screenshots/" + destinationFileObj.getName();
    }

    public static ExtentReports getExtentObj()
    {
        if(extent == null)
        {
            createExtentReport();
        }
        return extent;
    }

    public static ExtentReports createExtentReport()
    {
        htmlReporter = new ExtentHtmlReporter(projectPath + "/reports/BaufestWeb.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Reports");
        htmlReporter.config().setReportName("Automation Test Results: Baufest Web");
        htmlReporter.config().setTheme(Theme.STANDARD);
        //htmlReporter.setAppendExisting(true);

        extent = new ExtentReports();
        extent.setSystemInfo("Company", "Baufest");
        extent.setSystemInfo("Device", "MacBook Pro");
        extent.setSystemInfo("OS", "Catalina");
        extent.setSystemInfo("Browser", "Chrome");
        extent.attachReporter(htmlReporter);

        return extent;
    }
}
