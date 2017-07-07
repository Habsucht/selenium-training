package ru.stqa.selenium_training.tests.ui;

/**
 *  Test logon
 */

import org.junit.Test;

import org.openqa.selenium.*;

public class TestLogon extends BaseTests{

    @Test
    public void TestLogon() {
        wd.get("http://localhost/litecart/admin/login.php");
        wd.findElement(By.name("username")).click();
        wd.findElement(By.name("username")).clear();
        wd.findElement(By.name("username")).sendKeys("admin");
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).clear();
        wd.findElement(By.name("password")).sendKeys("admin");
        wd.findElement(By.name("login")).click();
    }
}