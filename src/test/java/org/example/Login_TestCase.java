package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Login_TestCase {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void testValidLogin() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void testInvalidPassword() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("wrongpass");
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(2000);
        String errorMsg = driver.findElement(By.cssSelector(".oxd-alert-content-text")).getText();
        Assert.assertEquals(errorMsg, "Invalid credentials");
    }



    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
