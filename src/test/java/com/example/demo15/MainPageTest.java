package com.example.demo15;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class MainPageTest {
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
    public void searchForSelenium() {
        String input = "Selenium";
        WebElement searchFeald = driver.findElement(By.cssSelector("#sb_form_q"));
        searchFeald.sendKeys(input);
        searchFeald.submit();

        List<WebElement> searchResults = driver.findElements(By.cssSelector("h2 > a[href]"));
        assertTrue(searchResults.size() > 0, "Результаты поиска не найдены.");

        WebElement firstResult = searchResults.get(1);
        clickElement(firstResult);

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.selenium.dev/", currentUrl);
    }

    public void clickElement(WebElement element) {
        System.out.println("Клик по элементу: " + element.getText());
        element.click();
    }
}
