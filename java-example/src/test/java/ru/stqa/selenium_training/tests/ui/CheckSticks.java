package ru.stqa.selenium_training.tests.ui;

import org.junit.Test;
import org.openqa.selenium.*;

import java.util.List;

public class CheckSticks extends BaseTests{

    @Test
    public void CheckSticks() {
        wd.get("http://localhost/litecart/");

        List<WebElement> productList = wd.findElements(By.xpath("//li[@class='product column shadow hover-light']"));

        for (WebElement element : productList) {

            System.out.println("Товар: " + element.findElement(By.className("name")).getText());

            List<WebElement> sticksList = element.findElements(By.cssSelector("[class *= sticker]"));

            if (sticksList.size() == 1) {
                System.out.println("У товара один стикер \"" + element.findElement(By.cssSelector("[class *= sticker]")).getText() + "\" - check");
            } else if (sticksList.size() == 0) {
                System.out.println("У товара нет стикера - error");
            } else if (sticksList.size() > 1) {
                System.out.println("У товара несколько стикеров - error");
            }
        }
    }
}


