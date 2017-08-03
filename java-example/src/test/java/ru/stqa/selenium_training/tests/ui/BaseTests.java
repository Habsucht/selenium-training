package ru.stqa.selenium_training.tests.ui;

/**
 *  The basic test class for initialization and termination
 */

import org.junit.After;
import org.junit.Before;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class BaseTests {
    static WebDriver wd;
    String browser;


    @Before
    public void setUp() throws Exception {
        if (browser == null) {
            browser = "FIREFOX";
        }

        switch (browser) {
            case "FIREFOX":
                wd = new FirefoxDriver();
                break;
            case "FIREFOX_NIGHTLY":
                FirefoxOptions options_nightly = new FirefoxOptions().setLegacy(false);
                options_nightly.setBinary("C:/Program Files/Nightly/firefox.exe");
                wd = new FirefoxDriver(options_nightly);
                break;
            case "FIREFOX_OLD":
                FirefoxOptions options_old = new FirefoxOptions().setLegacy(true);
                options_old.setBinary("D:/Browser's/Firefox_x64_45.0.1/firefox.exe");
                wd = new FirefoxDriver(options_old);
                break;
            case "CHROME":
                wd = new ChromeDriver();
                break;
            case "IE":
                wd = new InternetExplorerDriver();
                break;
        }
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        wd.quit();
    }

    void inputData(String tagName, String data) {
        wd.findElement(By.name(tagName)).click();
        wd.findElement(By.name(tagName)).clear();
        wd.findElement(By.name(tagName)).sendKeys(data);
    }
}