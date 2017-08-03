package ru.stqa.selenium_training.tests.ui;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

public class CheckAddNewProduct extends BaseTests{

    @Test
    public void CheckAddNewProduct() {
        TestLogon.logonUserOnAdminPage("admin", "admin");

        wd.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        int countProduct = wd.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']")).size();
        System.out.println(countProduct);

        // Page - General
        wd.get("http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product");

        wd.findElement(By.cssSelector("label")).click();
        if (!wd.findElement(By.name("status")).isSelected()) {
            wd.findElement(By.name("status")).click();
        }

        inputData("name[en]", "name");
        inputData("code", "code");


        if (!wd.findElement(By.xpath("//table//input[@name='product_groups[]' and @value='1-3']")).isSelected()) {
            wd.findElement(By.xpath("//table//input[@name='product_groups[]' and @value='1-3']")).click();
        }

        inputData("quantity","4");

        String path = new File(".").getAbsolutePath();
        wd.findElement(By.name("new_images[]")).sendKeys(path.substring(0, path.length() - 1) + "\\src\\test\\resources\\img1.jpg");

        // Page - Information
        wd.findElement(By.linkText("Information")).click();

        WebElement manufacturer_id = wd.findElement(By.cssSelector("select[name=manufacturer_id]"));
        manufacturer_id.click();
        List<WebElement> country = manufacturer_id.findElements(By.cssSelector("option"));
        for (WebElement element : country) {
            if (element.getText().equals("ACME Corp.")) {
                element.click();
                System.out.println("    |----> manufacturer_id " + element.getText());
            }
        }

        inputData("keywords","1");
        inputData("short_description[en]","2");
        inputData("head_title[en]","4");
        inputData("meta_description[en]","5");

        // Page - Prices
        wd.findElement(By.linkText("Prices")).click();

        inputData("purchase_price", "10");

        wd.findElement(By.name("save")).click();

        wd.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        System.out.println(wd.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']")).size() == countProduct + 1 ?
                "CHECK: Добавился новый товар" :
                "ERROR: Что-то пошло не так");
    }
}
