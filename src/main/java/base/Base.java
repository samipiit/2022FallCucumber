package base;

import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

public class Base {

    public static WebDriver driver;
    public static WebDriverWait webDriverWait;
    public static Wait<WebDriver> fluentWait;
    public static JavascriptExecutor jsDriver;
    public static Properties config;
    private static long driverTimeoutSeconds;
    private static long driverPollingIntervalMs;
    public static String browser;
    public static String url;

    public Base() {
        config = Config.loadProperties();
        url = config.getProperty("url");
        browser = config.getProperty("browser");
        driverTimeoutSeconds = Long.parseLong(config.getProperty("driver_timeout_seconds"));
        driverPollingIntervalMs = Long.parseLong(config.getProperty("driver_polling_frequency_ms"));

    }

    // region Selenium API
    public WebElement getVisibleElement(By by) {
        try {
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver.findElement(by);
    }

    public WebElement getClickableElement(By by) {
        try {
            fluentWait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver.findElement(by);

    }

    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);

        fluentWait.until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).perform();
    }

    public String getTrimmedElementText(WebElement element) {
        String text = "";
        fluentWait.until(ExpectedConditions.visibilityOf(element));

        text = element.getText().trim();

        if (text.equals("")) {
            text = element.getAttribute("innerHTML").trim();
        }

        return text;
    }

    public void clickOnElement(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendKeysToElement(WebElement element, String keys) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(keys);
    }

    public void clearSendKeysToElement(WebElement element, String keys) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(keys);
    }

    public void selectFromDropdownByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public void selectFromDropdownByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void selectFromDropdownByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void uploadFile(WebElement element, String absFilePath) {
        element.sendKeys(absFilePath);
    }

    public boolean isElementVisible(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isElementInvisible(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            return false;
        }

        return true;
    }

    public void waitForInvisibilityOfElement(WebElement element) {
        fluentWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void switchToParentFrame() {
        driver.switchTo().defaultContent();
    }

    public void switchToFrameByElement(WebElement frame) {
        driver.switchTo().frame(frame);
    }

    public void switchToTab() {
        String parentHandle = driver.getWindowHandle();

        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
            }
        }
    }

    // region JavaScriptExecutor Methods
    // TODO - Unit test and refactor as needed
    public String jsGetTrimmedElementText(WebElement element) {
        jsDriver = (JavascriptExecutor) (driver);
        String query = "arguments[0].getPropertyValue('innerHTML');";

        return jsDriver.executeScript(query, element).toString();
    }

    // TODO - Unit test and refactor as needed
    public WebElement findElementByXPathJS(String xPath) {
        jsDriver = (JavascriptExecutor) (driver);
        String query = String.format("document.getElement(By.xpath(\"%s\")", xPath);
        return (WebElement) (jsDriver.executeScript(query));
    }

    public void jsClickOnElement(WebElement element) {
        jsDriver = (JavascriptExecutor) (driver);
        jsDriver.executeScript("arguments[0].click();", element);
    }

    public void safeClickOnElement(WebElement element) {
        try {
            clickOnElement(element);
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            System.out.println("Unable to click - trying again");
            jsClickOnElement(element);
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Unable to locate element - check element locator and ensure element is being made available");
        } catch (ElementNotVisibleException e) {
            jsClickOnElement(element);
        }
    }

    public WebElement setElementAttributeValue(String attribute, String value, By by) {
        jsDriver = (JavascriptExecutor) (driver);
        jsDriver.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", driver.findElement(by));

        return driver.findElement(by);
    }

    // endregion

    // endregion

    // region Helpers
    public static void driverInit(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }

        webDriverWait = new WebDriverWait(driver, driverTimeoutSeconds);
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(driverTimeoutSeconds))
                .pollingEvery(Duration.ofMillis(driverPollingIntervalMs))
                .ignoring(Exception.class);
        jsDriver = (JavascriptExecutor) driver;
    }

    // endregion

}
