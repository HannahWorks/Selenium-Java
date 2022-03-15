import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSeleniumTest {
    public void firsTest()
    {
        //Telling the system where to find chromedriver. On Windows you also need to add .exe
        System.setProperty("webdriver.chrome.driver","resources/windows/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
    }
}
