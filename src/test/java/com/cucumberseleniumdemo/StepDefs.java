package com.cucumberseleniumdemo;

import java.util.concurrent.TimeUnit;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

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

            System.setProperty("webdriver.chrome.driver", "/var/lib/jenkins/jobs/CucumberTest/workspace/src/test/java/com/cucumberseleniumdemo/chromedriver");
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
        
    @When("User logs in using valid credentials")
    public void user_logs_in_using_valid_credentials() 
    {
        driver.findElement(By.id("signin_userName")).sendKeys("abc6@gmail.com");
    
        driver.findElement(By.id("signin_password")).sendKeys("P@33w0rd");
    }

    @Then("User should be logged in")
    public void user_is_logged_in() 
    {
        driver.findElement(By.id("genericLogin-button")).click();
            
        System.out.println("Home page is displayed");
            
        driver.close();
    }
    
    @When("User logs in using invalid credentials")
    public void user_logs_in_using_invalid_credentials() 
    {
        driver.findElement(By.id("signin_userName")).sendKeys("abc6@gmail.com");
    
        driver.findElement(By.id("signin_password")).sendKeys("P@33w0rd");
    }
    
    
    @Then("User should not be logged in")
     public void user_is_not_logged_in() 
     {
          driver.findElement(By.id("genericLogin-button")).click();
          try 
          {
                String errmsg = "Login Failed. Username or Password is incorrect.";

                String msg = driver.findElements(By.xpath("//*[@id='loginError']")).getText();

                Assert.assertFalse(errmsg.equals(msg));

                driver.close();
           }
          catch (NoSuchElementException e) 
            {
//                 return false;
            }
    }
}
