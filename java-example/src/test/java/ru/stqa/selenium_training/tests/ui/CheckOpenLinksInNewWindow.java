package ru.stqa.selenium_training.tests.ui;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class CheckOpenLinksInNewWindow extends BaseTests {

    @Test
    public void CheckOpenLinksInNewWindow() {
        TestLogon.logonUserOnAdminPage("admin", "admin");
        wd.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> countries = wd.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']"));
        ArrayList<String> countriesEditLinks = new ArrayList<>();

        for (WebElement country : countries) {
            countriesEditLinks.add(country.findElement(By.xpath(".//a[@title='Edit']")).getAttribute("href"));
        }

        for (String links : countriesEditLinks) {
            wd.get(links);

            List<WebElement> linksToOtherSites = wd.findElements(By.xpath("//td[@id='content']//td//a[@target='_blank']"));
            System.out.println("    >>> Найдено внешних ссылок - " + linksToOtherSites.size());

            String mainWindow = wd.getWindowHandle();
            String newWindow = null;
            System.out.println("mainWindow >>> " + mainWindow);

            for (WebElement link : linksToOtherSites) {

                Set<String> startAllWindows = wd.getWindowHandles();
                System.out.println("Список открытых окон:");
                for (String window : startAllWindows) {
                    System.out.println("    |---- " + window);
                }

                link.click();

                Set<String> finalAllWindows = wd.getWindowHandles();

                finalAllWindows.removeAll(startAllWindows);
                if (finalAllWindows.size() == 1) {
                    System.out.println("    >>> Открыто новое окно: ");
                    for (String window : finalAllWindows) {
                        System.out.println(window);
                        newWindow = window;
                    }
                } else if (finalAllWindows.size() > 1){
                    System.out.println("    >>> Открыты новые окна: ");
                    for (String window : finalAllWindows) {
                        System.out.println(window);
                    }
                } else {
                    System.out.println("ERROR: Новые окна не открылись!!!");
                }

                wd.switchTo().window(newWindow);
                wd.close();
                wd.switchTo().window(mainWindow);

            }
        }


    }
}
