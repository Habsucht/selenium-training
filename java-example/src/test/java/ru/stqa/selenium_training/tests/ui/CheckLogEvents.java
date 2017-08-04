package ru.stqa.selenium_training.tests.ui;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckLogEvents extends BaseTests {

    @Test
    public void CheckLogEvents() {
        System.out.println(wd.manage().logs().getAvailableLogTypes());

        wd.manage().logs().get("browser").getAll().forEach(l -> System.out.println(l));

        TestLogon.logonUserOnAdminPage("admin", "admin");
        wd.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");


        List<WebElement> listProduct = wd.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']"));
        ArrayList<String> listLinksProduct = new ArrayList<>();

        for (WebElement linkProduct : listProduct) {
            listLinksProduct.add(linkProduct.findElement(By.xpath(".//td[3]//a")).getAttribute("href"));
            System.out.println(linkProduct.findElement(By.xpath(".//td[3]//a")).getAttribute("href"));
        }

        for (String link : listLinksProduct) {
            wd.get(link);

            wd.manage().logs().get("browser").getAll().forEach(l -> System.out.println(l));
        }
    }
}
