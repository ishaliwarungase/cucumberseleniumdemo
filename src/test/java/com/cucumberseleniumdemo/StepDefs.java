package com.cucumberseleniumdemo;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {
WebDriver driver;
@Before public void setUp(){ 
System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
driver = new ChromeDriver();
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
} 

@Given("User enters url")
public void user_enters_url() 
{
    driver.get("http://aws-demo.shopizer.com/shop/");

    driver.findElement(By.xpath("//*[@id='customerAccount']/a/span/text()")).click();

    driver.findElement(By.xpath("/html/body/nav[1]/div/ul[2]/li[2]/ul/li[2]/a")).click();
}

@And("He enters userName")
public void he_enters_userName() 
{
    driver.findElement(By.id("signin_userName")).sendKeys("abc6@gmail.com");
}

@When("He enters password")
public void he_enters_password() 
{
    driver.findElement(By.id("signin_password")).sendKeys("P@33w0rd");
}

@Then("Home page is displayed")
public void home_page_is_displayed() 
{
    System.out.println("home page");
    driver.findElement(By.id("genericLogin-button")).click();
}
}