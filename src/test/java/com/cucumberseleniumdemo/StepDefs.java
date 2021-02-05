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
import org.openqa.selenium.support.ui.Select;
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

        driver.get("http://aws-demo.shopizer.com/shop/");

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    } 

    @Given("User opens Registration Page")
      public void register_user()
      {

        Actions actions = new Actions(driver);

        WebElement menuOption = driver.findElement(By.linkText("My Account"));

        actions.moveToElement(menuOption).perform();

        WebElement subMenu = driver.findElement(By.xpath("/html/body/nav[1]/div/ul[2]/li[2]/ul/li[1]/a"));

        JavascriptExecutor executor = (JavascriptExecutor)driver;

        executor.executeScript("arguments[0].click();", subMenu);
      }

       @When("User enters information for registration")
       public void user_enters_information_for_registration() 
       {
          driver.findElement(By.id("firstName")).sendKeys("");

          driver.findElement(By.id("lastName")).sendKeys("");
                         
          Select country = new Select(driver.findElement(By.xpath("//*[@id='registration_country']")));
          country.selectByVisibleText("India");
           
          driver.findElement(By.id("hidden_zones")).sendKeys("");
           
          driver.findElement(By.id("emailAddress")).sendKeys("abcd@gmail.com");

          driver.findElement(By.id("password")).sendKeys("P@33w0rd");

          driver.findElement(By.id("passwordAgain")).sendKeys("P@33w0rd");

        }

       @Then("User should register")
       public void user_should_register() 
       {
           driver.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div[2]/form/button")).click();

           try 
          {
                String errmsg = "Unable to complete registration, please try again later";

                String msg = driver.findElement(By.xpath("//*[@id='customer.errors']")).getText();

                Assert.assertFalse(errmsg.equals(msg));
          }
          catch (Exception e) 
           {

           }
       }

    
    @Given("User opens Login Page")
    public void user_enters_url() 
    {
        Actions actions = new Actions(driver);

        WebElement menuOption = driver.findElement(By.linkText("My Account"));

        actions.moveToElement(menuOption).perform();

        WebElement subMenu = driver.findElement(By.xpath("/html/body/nav[1]/div/ul[2]/li[2]/ul/li[2]/a"));

        JavascriptExecutor executor = (JavascriptExecutor)driver;

        executor.executeScript("arguments[0].click();", subMenu);
    }
        
    @When("User enters username and password")
    public void user_enters_username_and_password() 
    {
        driver.findElement(By.id("signin_userName")).sendKeys("abc@gmail.com");
    
        driver.findElement(By.id("signin_password")).sendKeys("P@33w0rd");
    }

    @Then("User should be logged in")
    public void user_should_be_logged_in() 
    {
        driver.findElement(By.id("genericLogin-button")).click();
        
        try 
          {
                String errmsg = "Login Failed. Username or Password is incorrect.";

                String msg = driver.findElement(By.xpath("//*[@id='loginError']")).getText();

                Assert.assertFalse(errmsg.equals(msg));

            
           }
          catch (Exception e) 
            {

            }
            
        driver.close();
    }
      
}

