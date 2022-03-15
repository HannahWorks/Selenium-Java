package webDriver.methods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.Set;

public class WindowsFramesTest {
    WebDriver driver;
    WebElement element;

    @BeforeMethod
    public void testSetup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

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
    public void windowsFrames(){
        driver.navigate().to("https://the-internet.herokuapp.com/windows");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://the-internet.herokuapp.com/windows/new')");

        String  originalWindow = driver.getWindowHandle();
        Set handles = driver.getWindowHandles();
        handles.remove(originalWindow);

        String nextWindow = String.valueOf(handles.iterator().next());

        driver.switchTo().window(nextWindow);
        Assert.assertEquals(driver.getTitle(),"New Window");

        driver.close();
        driver.switchTo().window(originalWindow);
        Assert.assertEquals(driver.getTitle(),"The Internet");;

    }

    @Test
    public void childWindow(){
        driver.get("https://demoqa.com/browser-windows");

        // Open new child window within the main window
        driver.findElement(By.id("windowButton")).click();

        //Get handles of the windows
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                WebElement text = driver.findElement(By.id("sampleHeading"));
                System.out.println("Heading of child window is " + text.getText());
            }
        }
    }


    @Test
    public void frames()
    {
        driver.get("https://the-internet.herokuapp.com/nested_frames");;

        //"frame-top" is the top level, default view of the frames
        WebElement defaultFrame = driver.findElement(By.name("frame-top"));
        //we can switch frames by index. 0 is first, 1 is second...
        driver.switchTo().frame(1);
        //assert that we switched to the bottom frame.
        //If we didn't, NoSuchElementException would be thrown
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText(),"BOTTOM");

        //switchTo().parentFrame() moves focus to the parent frame
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        //assert that we switched to the left frame;
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText(),"LEFT");

        //switchTo().defaultContent() will move to the top most/default frame
        driver.switchTo().defaultContent();
        //Get the element's height with Name frame-top. If we weren't focused on this element,
        //findElement would throw a NoSuchElementException
        Assert.assertTrue(driver.findElement(By.name("frame-top")).getSize().height > 0);
    }



}
