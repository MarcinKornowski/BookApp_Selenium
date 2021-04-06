package com.marcin_selenium.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class TestConfig {

    public static WebDriver driver;

    @BeforeEach
    public void setUp() {
        String baseUrl = "https://ta-ebookrental-fe.herokuapp.com/login";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    public Actions createAction() {
        return new Actions(driver);
    }
}
