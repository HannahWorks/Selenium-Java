package Locating.elements;

import com.sun.source.tree.AssertTree;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocatingElementTest {
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        //1. Instantiate the driver
        driver = new ChromeDriver();
    }

    @AfterEach
    public void teardown() {
        //7.quit the driver
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public  void elementsQuiz1()
    {
        //2. navigate to the URL
        driver.get("https://www.saucedemo.com/");
        //3. Find element 4. Check the state
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
        //5. Take action //6. Record the result
        assertTrue(element.isDisplayed());
    }

}
