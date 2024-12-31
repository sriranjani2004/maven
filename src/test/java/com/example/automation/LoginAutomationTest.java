package com.example.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginAutomationTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();  // Only set up once
        driver = new ChromeDriver();  // Initialize the WebDriver
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Ensure WebDriver is quit after each test
        }
    }

    @Test
    public void testSuccessfulLogin() {
        // Navigate to the login page
        driver.get("https://the-internet.herokuapp.com/login");

        // Locate the username and password fields
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button.radius"));

        // Perform login
        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("SuperSecretPassword!");
        loginButton.click();

        // Validate successful login
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = driver.findElement(By.cssSelector(".flash.success")).getText();
        assertTrue(actualMessage.contains(expectedMessage), "Success message should contain expected text.");
    }

    @Test
    public void testFailedLogin() {
        // Navigate to the login page
        driver.get("https://the-internet.herokuapp.com/login");

        // Locate the username and password fields
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button.radius"));

        // Perform login with invalid credentials
        usernameField.sendKeys("invalidUser");
        passwordField.sendKeys("invalidPassword");
        loginButton.click();

        // Validate failed login
        String expectedMessage = "Your username is invalid!";
        String actualMessage = driver.findElement(By.cssSelector(".flash.error")).getText();
        assertTrue(actualMessage.contains(expectedMessage), "Error message should contain expected text.");
    }
}
