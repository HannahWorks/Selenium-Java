package webDriver.methods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;

public class WebDriverMethodsQuiz {

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



    /*Go Here "https://the-internet.herokuapp.com/dropdown"
    select option 1 from the dropdown
    assert that option 1 is selected
    assert that option 2 is not selected*/
    @Test
    public void testDropdown()
    {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByVisibleText("Option 1");
        String result = dropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(result,"Option 1");
        Assert.assertNotEquals(result,"Option 2");

        //another way to assert
        //WebElement element = driver.findElement(By.id("dropdown"));
        //element.click();
        //WebElement option1 = element.findElement(By.cssSelector("option[value='1']"));
        //WebElement option2 = element.findElement(By.xpath("//option[@value='2']"));
        //option2.click();
        //Assert.assertTrue(option2.isSelected());
        //Assert.assertFalse(option1.isSelected());
    }



    /*Go to "https://the-internet.herokuapp.com/hovers"
    Hover over the first image
    Assert that on hover there is text that is displayed beloow "name:user1"
*/

    @Test
    public void testHovers(){
        driver.navigate().to("https://the-internet.herokuapp.com/hovers");
        Actions action = new Actions(driver);
        element = driver.findElement(By.xpath("//*[@class='figure'][1]"));
        action.moveToElement(element).perform();
        element = element.findElement(By.className("figcaption"));
        Assert.assertTrue(element.isDisplayed(),"Details should be displayed when I hover over the image");
        String name = element.findElement(By.tagName("h5")).getText();
        Assert.assertEquals("name: user1",name);
    }

     /*"https://the-internet.herokuapp.com/context_menu"
    Right click
    close alert
*/
    @Test
    public void alertAcceptTest(){
        driver.get("https://the-internet.herokuapp.com/context_menu");
        element = driver.findElement(By.id("hot-spot"));
        Actions action = new Actions(driver);
        action.contextClick(element).perform();
        driver.switchTo().alert().accept();
    }




    /*"https://the-internet.herokuapp.com/key_presses"
    send right arrow key to the input box
    assert that you got this text back "You entered: RIGHT"
*/
    @Test
    public void TestKeyPresses(){
        driver.get("https://the-internet.herokuapp.com/key_presses");
        element = driver.findElement(By.id("target"));
        element.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_RIGHT).pause(1000).perform();
        element = driver.findElement(By.id("result"));
        Assert.assertEquals( element.getText(),"You entered: RIGHT","Clicked right arrow key");
    }

    //when you press the ENTER key
    @Test
    public void TestEnterKeyPresses(){
        driver.get("https://the-internet.herokuapp.com/key_presses");
        element = driver.findElement(By.id("target"));
        element.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).pause(1000).perform();
        //If you press Enter Key twice, you will pass the test
        //actions.sendKeys(Keys.ENTER).pause(1000).perform();
        element = driver.findElement(By.id("result"));
        Assert.assertEquals( element.getText(),"You entered: ENTER","Clicked the Enter key");
    }

   /* "https://ultimateqa.com/simple-html-elements-for-automation"
    find element with text "Clickable Icon"
    assert href attribute = https://ultimateqa.com/link-success/
    get css value: "background-origin"
    assert that it equals "padding-box"
*/
    @Test
    public void testElementsText(){
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation");
        element = driver.findElement(By.linkText("Clickable Icon"));
        Assert.assertEquals(element.getAttribute("href"),"https://ultimateqa.com/link-success/");
        Assert.assertEquals(element.getCssValue("background-origin"),"padding-box");
    }





}
