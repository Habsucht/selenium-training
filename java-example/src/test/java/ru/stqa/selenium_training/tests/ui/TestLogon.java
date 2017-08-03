package ru.stqa.selenium_training.tests.ui;

/**
 *  Test logon
 */

import org.junit.Test;

import org.openqa.selenium.*;

public class TestLogon extends BaseTests{

    @Test
    public void TestLogonAdmin() {
        logonUserOnAdminPage("admin", "admin");
    }

    @Test
    public void TestLogonUser() {
        logonUserOnMainPage("user@email.com", "user@email.com");
    }

    static void logonUserOnAdminPage(String login, String password) {
        wd.get("http://localhost/litecart/admin/login.php");
        wd.findElement(By.name("username")).click();
        wd.findElement(By.name("username")).clear();
        wd.findElement(By.name("username")).sendKeys(login);
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).clear();
        wd.findElement(By.name("password")).sendKeys(password);
        wd.findElement(By.name("login")).click();
    }

    static void logonUserOnMainPage(String email, String password) {
        wd.get("http://localhost/litecart/en/");
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(email);
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).clear();
        wd.findElement(By.name("password")).sendKeys(password);
        wd.findElement(By.name("login")).click();

        System.out.println(wd.findElements(By.cssSelector("#notices .success")) != null ? "CHECK: Logon OK" : "ERROR: Logon FAIL");
    }
}