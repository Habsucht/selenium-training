package ru.stqa.selenium_training.tests.ui;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.selenium_training.data.User;

public class CheckRegistrationNewUser extends BaseTests {

    @Test
    public void CheckRegistrationNewUser() {
        User newUser = new User();

        wd.get("http://localhost/litecart/en/");

        wd.findElement(By.linkText("New customers click here")).click();

        inputData("tax_id", newUser.getTax_id());
        inputData("company", newUser.getCompanyName());

        inputData("firstname", newUser.getFirstname());
        inputData("lastname", newUser.getLastname());

        inputData("address1", newUser.getAddress1());
        inputData("address2", newUser.getAddress2());

        inputData("postcode", newUser.getPostcode());
        inputData("city", newUser.getCity());

        // Make the select block visible through JavaScript execution
        WebElement elementCountryCode = wd.findElement(By.xpath("//select[@name='country_code']"));
        JavascriptExecutor js = (JavascriptExecutor)wd;
        js.executeScript("arguments[0].style.opacity=1", elementCountryCode);

        // The first variant of selecting an element of the drop-down list
        //
        //elementCountryCode.click();
        //List<WebElement> country = elementCountryCode.findElements(By.cssSelector("option"));
        //for (WebElement element : country) {
        //    if (element.getText().equals("United States")) {
        //        element.click();
        //        System.out.println("    |----> регион " + element.getText() + " выбран");
        //   }
        //}

        // The second variant of selecting an element of the drop-down list
        //
        // if (!wd.findElement(By.xpath("//option[@value='US']")).isSelected()) {
        //    wd.findElement(By.xpath("//option[@value='US']")).click();
        //}
        //if (!wd.findElement(By.xpath("//select[@name='zone_code']//option[@value='AK']")).isSelected()) {
        //    wd.findElement(By.xpath("//select[@name='zone_code']//option[@value='AK']")).click();
        //}

        // The third variant of selecting an element of the drop-down list
        Select selectCountryCode = new Select(elementCountryCode);
        selectCountryCode.selectByVisibleText("United States");

        Select selectZoneCode = new Select(wd.findElement(By.xpath("//select[@name='zone_code']")));
        selectZoneCode.selectByVisibleText("Arizona");

        inputData("email", newUser.getEmail());
        inputData("phone", newUser.getPhone());

        inputData("password", newUser.getPassword());
        inputData("confirmed_password", newUser.getPassword());

        wd.findElement(By.name("create_account")).click();

        try {
            wd.findElement(By.cssSelector("#notices .success"));
        } catch (NoSuchElementException ex) {
            System.out.println("ERROR: Что-то пошло не так\\n" + ex);
        }

        wd.findElement(By.linkText("Logout")).click();

        TestLogon.logonUserOnMainPage(newUser.getEmail(), newUser.getPassword());
    }
}
