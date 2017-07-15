package ru.stqa.selenium_training.tests.ui;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckValidOpenPage extends BaseTests{
    class Price {
        String price;
        String[] color;
        String lineThrough;
        String fontWeight;
        double fontSize;
    }

    @Test
    public void CheckValidOpenPage() {

        String regularPriceXPath = ".//s[@class='regular-price']";
        String campaignPriceXPath = ".//strong[@class='campaign-price']";

        wd.get("http://localhost/litecart/");

        WebElement productOnHomePage = wd.findElement(By.xpath("//*[@id='box-campaigns']/div/ul/li[1]"));

        String nameProduct1 = productOnHomePage.findElement(By.xpath(".//div[@class='name']")).getText();
        System.out.println("\n----------------------------------\nnameProduct1 - " + nameProduct1);

        //Regular/Campaign price and attribute
        Price regularPrice1 = getPriceAttribute("regularPrice:", regularPriceXPath, productOnHomePage);
        Price campaignPrice1 = getPriceAttribute("campaignPrice:", campaignPriceXPath, productOnHomePage);

        //GO TO linkProductPage
        wd.get(productOnHomePage.findElement(By.xpath(".//a")).getAttribute("href"));

        WebElement productPage = wd.findElement(By.xpath("//div[@id='box-product']"));

        String nameProduct2 = productPage.findElement(By.xpath(".//h1[@itemprop='name']")).getText();
        System.out.println("\n----------------------------------\nnameProduct2 - " + nameProduct2);

        //Regular/Campaign price and attribute
        Price regularPrice2 = getPriceAttribute("regularPrice:", regularPriceXPath, productPage);
        Price campaignPrice2 = getPriceAttribute("campaignPrice:", campaignPriceXPath, productPage);

        //Check name product and price
        System.out.println(nameProduct2.equals(nameProduct1) ?
                "CHECK: " + nameProduct1 + " == " + nameProduct2 :
                "ERROR: " + nameProduct1 + " != " + nameProduct2);
        System.out.println(regularPrice2.price.equals(regularPrice1.price) ?
                "CHECK: " + regularPrice1.price + " == " + regularPrice2.price :
                "ERROR: " + regularPrice1.price + " != " + regularPrice2.price);
        System.out.println(campaignPrice2.price.equals(campaignPrice1.price) ?
                "CHECK: " + campaignPrice1.price + " == " + campaignPrice2.price :
                "ERROR: " + campaignPrice1.price + " != " + campaignPrice2.price);

        //Check color
        System.out.println(regularPrice1.color[0].equals(regularPrice1.color[1]) & regularPrice1.color[0].equals(regularPrice1.color[2]) ?
                "CHECK: regularPrice1.color == GREY" :
                "ERROR: regularPrice1.color != GREY");
        System.out.println(!campaignPrice1.color[0].equals(regularPrice1.color[1]) & regularPrice1.color[1].equals(regularPrice1.color[2]) ?
                "CHECK: campaignPrice1.color == RED" :
                "ERROR: campaignPrice1.color != RED");
        System.out.println(regularPrice2.color[0].equals(regularPrice2.color[1]) & regularPrice2.color[0].equals(regularPrice2.color[2]) ?
                "CHECK: regularPrice2.color == GREY" :
                "ERROR: regularPrice2.color != GREY");
        System.out.println(!campaignPrice2.color[0].equals(regularPrice2.color[1]) & regularPrice2.color[1].equals(regularPrice2.color[2]) ?
                "CHECK: campaignPrice2.color == RED" :
                "ERROR: campaignPrice2.color != RED");

        //Check font
        System.out.println(regularPrice1.lineThrough.equals("line-through") ?
                "CHECK: regularPrice1.lineThrough == line-through" :
                "ERROR: regularPrice1.lineThrough != line-through");
        System.out.println(campaignPrice1.fontWeight.equals("bold") ?
                "CHECK: campaignPrice1.fontWeight == bold" :
                "ERROR: campaignPrice1.fontWeight != bold");

        //Check font size for price
        System.out.println(regularPrice1.fontSize < campaignPrice1.fontSize ?
                "CHECK: regularPrice1.fontSize < campaignPrice1.fontSize == true" :
                "ERROR: regularPrice1.fontSize < campaignPrice1.fontSize == false");
        System.out.println(regularPrice2.fontSize < campaignPrice2.fontSize ?
                "CHECK: regularPrice2.fontSize < campaignPrice2.fontSize == true" :
                "ERROR: regularPrice2.fontSize < campaignPrice2.fontSize == false");
    }

    Price getPriceAttribute(String info, String priceXPath, WebElement product) {
        Price price = new Price();
        String TEMP;

        System.out.println("\n" + info + "\n-----");

        price.price = product.findElement(By.xpath(priceXPath)).getText();
        System.out.println("    |-  Price - " + price.price);

        TEMP = product.findElement(By.xpath(priceXPath)).getCssValue("color");
        price.color = TEMP.substring(TEMP.indexOf("(") + 1, TEMP.indexOf(")") ).split(",");
        System.out.println("    |-  color - " + TEMP + " ->> result: " + price.color[0] + ", " + price.color[1] + ", " + price.color[2]);

        price.lineThrough = product.findElement(By.xpath(priceXPath)).getCssValue("text-decoration-line");
        System.out.println("    |-  lineThrough - " + price.lineThrough);

        price.fontWeight = product.findElement(By.xpath(priceXPath)).getCssValue("font-weight");
        System.out.println("    |-  fontWeight - " + price.fontWeight);

        TEMP = product.findElement(By.xpath(priceXPath)).getCssValue("font-size");
        price.fontSize = Double.parseDouble(TEMP.substring(0, TEMP.indexOf("p")));
        System.out.println("    |-  fontSize - " + price.fontSize + " px");

        return price;
    }
}
