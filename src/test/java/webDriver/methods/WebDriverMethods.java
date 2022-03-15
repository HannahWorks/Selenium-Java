package webDriver.methods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverMethods {
    WebDriver driver;
    WebElement element;

    @BeforeMethod
    public void testSetup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @AfterMethod
    public void teardown()
    {
        if(driver!= null)
        {
            driver.quit();
        }
    }

    @Test
    public void alerts() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//*[contains(normalize-space(text()),'Click for JS Alert')]")).click();
        driver.switchTo().alert().dismiss();

        driver.findElement(By.xpath("//*[contains(normalize-space(text()),'Click for JS Confirm')]")).click();
        driver.switchTo().alert().accept();

        driver.findElement(By.xpath("//*[contains(normalize-space(text()),'Click for JS Prompt')]")).click();
        Alert inputAlert = driver.switchTo().alert();
        String text = inputAlert.getText();
        inputAlert.sendKeys("Look Mom! I can automate alerts :)");
        inputAlert.accept();
    }

    // open url https://example.cypress.io/commands/actions
    // Focus on element By.cssSelector(".action-focus")
    // Assert is focused
    @Test
    public void actions() {

        driver.get("https://example.cypress.io/commands/actions");

        element = driver.findElement(By.cssSelector(".action-focus"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        Assert.assertTrue(
                driver.findElement(By.xpath("//*[@for='password1']")).
                        getAttribute("style").contains("color: orange;"));
    }

    /*
     * Go to https://example.cypress.io/commands/cookies
     * Set a cookie using button
     * Get the cookie named "token"
     * Assert that we have a cookie value equal to "123ABC"
     * */
    @Test
    public void cookies() {
        driver.get("https://example.cypress.io/commands/cookies");
        element = driver.findElement(By.cssSelector(".set-a-cookie"));
        element.click();

        var cookie = driver.manage().getCookieNamed("token");
        Assert.assertEquals( cookie.getValue(),"123ABC");
    }


    /*
     * Go to https://ultimateqa.com/complicated-page/
     * scroll to bottom of page using JS executeScript command:
     *
     * window.scrollTo(0, document.body.scrollHeight)
     * Thread.sleep(1000);
     * */
    @Test
    public void scrollToBottom() throws InterruptedException {
        driver.navigate().to("https://ultimateqa.com/complicated-page/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);
    }

    /*
     * Go to https://ultimateqa.com/complicated-page/
     * scroll to top of page using JS executeScript command:
     *
     * window.scrollTo(0, -document.body.scrollHeight)
     * Thread.sleep(1000);
     * */
    @Test
    public void scrollToTop() throws InterruptedException {
        driver.navigate().to("https://ultimateqa.com/complicated-page/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);
        //Now scroll to the top
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        Thread.sleep(1000);
    }

    @Test
    public void howToHighlightElement() throws InterruptedException {
        driver.navigate().to("https://ultimateqa.com/complicated-page/");
        element = driver.findElement(By.id("Skills_Improved"));
        // Get the style of the element and store it into a variable
        var originalStyle = element.getAttribute("style");

        // execute a JS command that will create a yellow border around the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",
                element,
                "style",
                "border: 7px solid yellow; border-style: dashed;");
        Thread.sleep(2000);

        // using JS, set the style of the element back to the original
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",
                element,
                "style",
                originalStyle);
    }

}
