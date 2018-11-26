package common.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import read.xpath.properties.ReadProperty;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Common {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebElement receipt;
    boolean flag;
    String locator;
    private String URL="";
    private String userName="";
    private String password="";

    ReadProperty readProperty=new ReadProperty();

    public void openFirefox(){
        System.setProperty("webdriver.gecko.driver", "D:\\Drivers\\Firefox\\geckodriver.exe");
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("marionette", true);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.privatebrowsing.autostart",true);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(firefoxProfile);
        driver = new FirefoxDriver(options);
        driver.get(URL);
        locator=readProperty.getValueOfKey("username");
        userName=readProperty.getValueOfKey("facebookUsername");
        sendText(locator,userName);
        locator=readProperty.getValueOfKey("password");
        password=readProperty.getValueOfKey("facebookPassword");
        sendText(locator,password);
        locator=readProperty.getValueOfKey("login");
        clickButton(locator);
    }

    public void openChrome(){
        System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\Chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        locator=readProperty.getValueOfKey("username");
        sendText(locator,userName);
        locator=readProperty.getValueOfKey("password");
        sendText(locator,password);
        locator=readProperty.getValueOfKey("login");
        clickButton(locator);
    }

    public void openBrowser(){
        openFirefox();
    }

    public WebDriver switchToiFrame(){
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        int iFrameSize = driver.findElements(By.tagName("iframe")).size();
        for (int frameIndex = iFrameSize-1; frameIndex > -1; frameIndex--) {
            driver.switchTo().frame(frameIndex);
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            try {
                receipt = driver.findElement(By.className("receipt-wrapper"));
                break;
            } catch (NoSuchElementException e) {
                System.out.println("FrameIndex:" + frameIndex + ", No element found!");
            }
        }
        return driver;
    }

    public WebDriver switchToDefultContent(){
        driver.switchTo().parentFrame();
        return driver;
    }

    public boolean verifyElementListExistIniFrame(String element){
        boolean flag=false;
        List<WebElement> elements = receipt.findElements(By.xpath(element));
        for(int i=0;i<elements.size();i++){
            flag=elements.get(i).isDisplayed();
        }
        return flag;
    }

    public void waitTillElementVisible(By locator) {
        try {
            wait = new WebDriverWait(driver, 50);
            List<WebElement> titles = wait.until
                    (ExpectedConditions.
                            visibilityOfAllElementsLocatedBy
                                    (locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyExistIniFrame(String element){
        WebElement webElement=receipt.findElement(By.xpath(element));
        flag=webElement.isDisplayed();
        return flag;
    }

    public void clickButtonIniFrame(String element){
        receipt.findElement(By.xpath(element)).click();
    }

    public String verifyGetText(String element){
        String text=receipt.findElement(By.xpath(element)).getText();
        return text;
    }

    public List<WebElement> returnElementListInIFrame(String locator){
        List<WebElement> elements=receipt.findElements(By.xpath(locator));
        return elements;
    }

    public void scrollDown(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 600);");
    }

    public void closeBrowser(){
        driver.switchTo().defaultContent();
        driver.close();
    }

    public void sendText(String locator,String textValue){
        driver.findElement(By.id(locator)).sendKeys(textValue);
    }

    public void clickButton(String element){
        driver.findElement(By.id(element)).click();
    }
}
