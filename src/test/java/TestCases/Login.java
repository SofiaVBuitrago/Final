package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Login {
    WebDriver driver = null;

    @BeforeMethod
    public void openBrowser(){

        String browserName = "firefox";
        if(browserName.equals("chrome")){
            driver = new ChromeDriver();
        }else if (browserName.equals("firefox")){
            driver = new FirefoxDriver();
        } else if ( browserName.equals("edge")) {
            driver = new EdgeDriver();
        }
        String URL = "https://tutorialsninja.com/demo";
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li[2]/a")).click();

    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
    @Test(priority = 1)
    public void validCredentials(){

        driver.findElement(By.name("email")).sendKeys("pruebatestingsofia@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input[value='Login']")).click();
        String verify = driver.findElement(By.xpath("(//div[@id=\"account-account\"]/div/div/ul/li/a)[1]")).getText();

        Assert.assertEquals(verify,"Edit your account information");
        //Assert.assertTrue(driver.findElement(By.xpath("(//div[@id='account-account']/div/div/ul/li/a)[1]")).isDisplayed());


    }

    @Test(priority = 2)
    public void invalidCredentials(){

         driver.findElement(By.name("email")).sendKeys("test" + randomEmail() + "@gmail.com");
        driver.findElement(By.name("password")).sendKeys("1234567");
        driver.findElement(By.cssSelector("input[value='Login']")).click();
        String warning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();

        Assert.assertEquals(warning,"Warning: No match for E-Mail Address and/or Password.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).isDisplayed());

    }

    public String randomEmail (){
        Date date = new Date();
        String timestamp =  date.toString().replace(" ","_").replace(":","_");
        return  "test" + timestamp + ""
    }

    @Test(priority = 3)
    public void invalidEmail(){

        driver.findElement(By.name("email")).sendKeys("test" + randomEmail() + "@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input[value='Login']")).click();
        String warning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();

        Assert.assertEquals(warning,"Warning: No match for E-Mail Address and/or Password.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).isDisplayed());

    }
    @Test(priority = 4)
    public void invalidPassword(){

        driver.findElement(By.name("email")).sendKeys("pruebatestingsofia@gmail.com");
        driver.findElement(By.name("password")).sendKeys("1234567");
        driver.findElement(By.cssSelector("input[value='Login']")).click();
        String warning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();

        Assert.assertEquals(warning,"Warning: No match for E-Mail Address and/or Password.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).isDisplayed());

    }

    @Test(priority = 5)
    public void emptyCredentials(){

        driver.findElement(By.cssSelector("input[value='Login']")).click();
        String warning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();

        Assert.assertEquals(warning,"Warning: No match for E-Mail Address and/or Password.");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).isDisplayed());

    }

}
