package com.example.demo15.tests;

import com.example.demo15.pages.MainPage;
import com.example.demo15.pages.ResultsPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class BingSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchResultsTest() {
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage rp = new ResultsPage(driver);
        rp.clickElement(1);
        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl(), "Открылась не верная ссылка");
    }

    public void clickElement(WebElement element) {
        System.out.println("Клик по элементу: " + element.getText());
        element.click();
    }

    @Test
    public void searchTest() {
        String input = "Selenium";

        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage rp = new ResultsPage(driver);


        assertEquals(input, rp.getTextFromSearchField(), "Текст не совпал");
    }



}


