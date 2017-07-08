package ru.stqa.selenium_training.tests.ui;

import org.junit.Test;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class CheckLeftMenu extends BaseTests{

    @Test
    public void CheckLeftMenu1() {
        TestLogon.logon();

        ArrayList<String> leftMenuList = new ArrayList<>();
        List<WebElement> leftMenu = wd.findElements(By.xpath("//ul[@id='box-apps-menu']/li"));

        for (WebElement element : leftMenu) {
            leftMenuList.add(element.findElement(By.tagName("a")).getText());
        }

        for (int i = 0; i < leftMenuList.size(); i++) {
            wd.findElement(By.linkText(leftMenuList.get(i))).click();
            System.out.print(leftMenuList.get(i) + " - go to page: check");

            searchTag("h1");

            ArrayList<String> leftSubMenuList = new ArrayList<>();
            List<WebElement> subMenu = wd.findElements(By.xpath("//ul[@class='docs']/li"));

            if (subMenu.size() > 0) {
                for (WebElement element : subMenu) {
                    leftSubMenuList.add(element.findElement(By.tagName("a")).getText());
                }

                for (int n = 0; n < leftSubMenuList.size(); n++) {
                    wd.findElement(By.linkText(leftSubMenuList.get(n))).click();
                    System.out.print("  |-" + leftSubMenuList.get(n) + " - go to page: check");

                    searchTag("h1");
                }
            }
        }
    }

    void searchTag(String tag) {
        try {
            System.out.println("           tag \"" + tag + "\" " + wd.findElement(By.tagName(tag)).getText());
        } catch (NoSuchElementException ex) {
            System.out.println("           tag \"" + tag + "\" not found");
        }
    }
}
