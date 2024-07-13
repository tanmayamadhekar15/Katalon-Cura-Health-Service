package com.automationtestingrestassured.KatalonCuraDemo;

import io.qameta.allure.Allure;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginIn {

    WebDriver driver;
    @BeforeTest
    void openBrowser(){
        driver=new ChromeDriver();
    }

    @Test
    public void testBrowser() throws InterruptedException {
        driver.get("https://katalon-demo-cura.herokuapp.com/");  //launch browser

        driver.manage().window().maximize();

        WebElement makeApt=driver.findElement(By.id("btn-make-appointment"));
        makeApt.click();

        WebElement afterlogin=driver.findElement(By.tagName("h2"));
        assertEquals("Login", afterlogin.getText(), "Successfully logged in");

        WebElement username= driver.findElement(By.xpath("//input[@id=\"txt-username\"]"));
        username.sendKeys("John Doe");

        WebElement password= driver.findElement(By.xpath("//input[@id=\"txt-password\"]"));
        password.sendKeys("ThisIsNotAPassword");

        WebElement login= driver.findElement(By.xpath("//button[@id=\"btn-login\"]"));
        login.click();

        WebElement title = driver.findElement(By.tagName("h1"));
        title.getText();

        //  Verify that a certain element is displayed
        WebElement nextPage = driver.findElement(By.tagName("h2"));
        assertTrue(nextPage.isDisplayed(), "Make Appointment");


        WebElement date=driver.findElement(By.id("txt_visit_date"));
        date.sendKeys("12/07/2024");
        WebElement comment=driver.findElement(By.id("txt_comment"));
        comment.sendKeys("Viral fever");
        WebElement bookApt=driver.findElement(By.id("btn-book-appointment"));
        bookApt.click();

        WebElement bookedApt=driver.findElement(By.xpath("//div[@class=\"col-xs-12 text-center\"]/h2"));
        assertThat(bookedApt.getText(), Matchers.equalTo("Appointment Confirmation"));

        Thread.sleep(5000);
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
