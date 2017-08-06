package ru.stqa.selenium_training.tests.ui;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CheckActionsWithBasketGoods extends BaseTests {

    @Test
    public void CheckActionsWithBasketGoods() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(wd, 10/*seconds*/);

        for (int i = 0 ; i < 3 ; i++) {
            wd.get("http://localhost/litecart/en/");
            WebElement firstProduct = wd.findElement(By.cssSelector(".product"));
            wd.get(firstProduct.findElement(By.xpath(".//a")).getAttribute("href"));

            int checkProductInCartStart1 = Integer.parseInt(wd.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']")).getText());
            System.out.println("Товаров в корзине - " + checkProductInCartStart1);

            try {
                if (!wd.findElement(By.xpath("//td[@class='options']/select//option[2]")).isSelected()) {
                    wd.findElement(By.xpath("//td[@class='options']/select//option[2]")).click();
                }
            } catch (NoSuchElementException ex) {
                System.out.println("У товара нет специфических параметров");
            }

            inputData("quantity", "1");
            wd.findElement(By.name("add_cart_product")).click();

            for (; ; ) {
                int checkProductInCartEnd1 = Integer.parseInt(wd.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']")).getText());

                if (checkProductInCartEnd1 > checkProductInCartStart1) {
                    System.out.println("Число товаров в корзине увеличилось на " + (checkProductInCartEnd1 - checkProductInCartStart1));
                    break;
                } else {
                    System.out.println("WAIT...");
                    Thread.sleep(1000);
                }
            }
        }

        wd.get("http://localhost/litecart/en/");
        int checkProductInCartStart2 = Integer.parseInt(wd.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']")).getText());
        System.out.println("    >>> Товаров в корзине - " + checkProductInCartStart2);

        wd.get("http://localhost/litecart/en/checkout");
        int checkProductInTableBeforeRemoval;

        for (int i = 0 ; i < checkProductInCartStart2 ; i++) {
            wd.navigate().refresh();
            ArrayList<WebElement> table = (ArrayList<WebElement>) wd.findElements(By.xpath("//table//td[@class='item']"));

            checkProductInTableBeforeRemoval = table.size();
            wd.findElement(By.xpath("//button[@value='Remove']")).click();

            for (; ; ) {
                int checkProductInTableAfterRemoval = wd.findElements(By.xpath("//table//td[@class='item']")).size();

                if (checkProductInTableBeforeRemoval > checkProductInTableAfterRemoval) {
                    System.out.println("Число товаров в таблице уменьшилось до " + checkProductInTableAfterRemoval);
                    break;
                } else {
                    System.out.println("WAIT...");
                    wait.until(stalenessOf(table.get(table.size() - 1)));
                }
            }
        }
    }
}
