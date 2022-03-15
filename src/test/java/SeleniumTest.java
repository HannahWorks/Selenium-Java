import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

public class SeleniumTest {
    //This is a test

    // Navigate to this URL using another browser https://the-internet.herokuapp.com/
    // Navigate to this URL https://example.cypress.io/
    // Navigate to https://react-shopping-cart-67954.firebaseapp.com/
    // Using WebDriverManager

    //Try using TestNG instead Junit

    WebDriver driver;

    @BeforeClass
    public void setupClass()
    {
        WebDriverManager.edgedriver().setup();
    }
    @BeforeMethod
    public void setupTest()
    {
        driver = new EdgeDriver();
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
    public void firstUrlTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/");
        Thread.sleep(3000);
    }

    @Test
    public void secondUrlTest() throws InterruptedException {
        driver.get("https://example.cypress.io/");
        Thread.sleep(3000);
    }

    @Test
    public void thirdUrlTest() throws InterruptedException {
        driver.get("https://react-shopping-cart-67954.firebaseapp.com/");
        Thread.sleep(3000);
    }


}
