package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Login {
    WebDriver driver = null;
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
    @Test
    public void validCredentials(){

        driver = new ChromeDriver();
        String URL = "https://tutorialsninja.com/demo";
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li[2]/a")).click();
        driver.findElement(By.name("email")).sendKeys("pruebatestingsofia@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input[value='Login']")).click();
        String verify = driver.findElement(By.xpath("(//div[@id=\"account-account\"]/div/div/ul/li/a)[1]")).getText();

        Assert.assertEquals(verify,"Edit your account information");
        //Assert.assertTrue(driver.findElement(By.xpath("(//div[@id='account-account']/div/div/ul/li/a)[1]")).isDisplayed());


    }

    @Test
    public void invalidCredentials(){

        driver = new ChromeDriver();
        String URL = "https://tutorialsninja.com/demo";
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li[2]/a")).click();
        driver.findElement(By.name("email")).sendKeys("pruebatestingsofia@gmail.com");
        driver.findElement(By.name("password")).sendKeys("1234567");
        driver.findElement(By.cssSelector("input[value='Login']")).click();
        String warning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();

        //Assert.assertEquals(warning,"Warning: No match for E-Mail Address and/or Password.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).isDisplayed());

    }
}
