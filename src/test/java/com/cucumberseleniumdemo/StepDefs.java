package com.cucumberseleniumdemo;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import static org.junit.Assert.*;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs 
{
        WebDriver driver;
        @Before public void setUp()
        { 

            System.setProperty("webdriver.chrome.driver", "/var/lib/jenkins/workspace/CucumberSeleniumDemo/src/test/java/com/cucumberseleniumdemo/chromedriver");
            ChromeOptions options = new ChromeOptions().setHeadless(true);
            driver = new ChromeDriver(options);
        } 

    @Given("User enters URL")
    public void user_enters_url() 
    {
        driver.get("http://aws-demo.shopizer.com/shop/");

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

        Actions actions = new Actions(driver);

        WebElement menuOption = driver.findElement(By.linkText("My Account"));

        actions.moveToElement(menuOption).perform();

        WebElement subMenu = driver.findElement(By.xpath("/html/body/nav[1]/div/ul[2]/li[2]/ul/li[2]/a"));

        JavascriptExecutor executor = (JavascriptExecutor)driver;

        executor.executeScript("arguments[0].click();", subMenu);
    }

    @And("He enters userName")
    public void he_enters_userName() 
    {
        driver.findElement(By.id("signin_userName")).sendKeys("abc@gmail.com");
    }

    @When("He enters password")
    public void he_enters_password() 
    {
        driver.findElement(By.id("signin_password")).sendKeys("P@33w0rd");
    }

    @Then("Home page is displayed")
    public void home_page_is_displayed() 
    {
        driver.findElement(By.id("genericLogin-button")).click();

        String msg = driver.findElement(By.xpath('//*[@id="loginError"]')).getText();

        String errmsg = "Login Failed. Username or Password is incorrect.";

        assertEquals(msg,errmsg);

        if(msg == errmsg)
        {
            System.out.println("Login Failed. Username or Password is incorrect");
        }
        else
        {
            System.out.println("Home page is displayed");
        }
        driver.close();
    }
}
