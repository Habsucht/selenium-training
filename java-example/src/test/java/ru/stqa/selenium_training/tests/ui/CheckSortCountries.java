package ru.stqa.selenium_training.tests.ui;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckSortCountries extends BaseTests {

    @Test
    public void CheckSortCountries() {
        TestLogon.logonAdmin();
        wd.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        ArrayList<String> unsortedCountriesList = new ArrayList<>();
        ArrayList<String> sortedCountriesList = new ArrayList<>();

        ArrayList<String> multiZoneCountry = new ArrayList<>();

        List<WebElement> countries = wd.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']"));

        System.out.println(countries.size());

        for (int i = 1; i < countries.size(); i++) {
            unsortedCountriesList.add(countries.get(i).findElement(By.xpath("//td[5]")).getText());
            sortedCountriesList.add(countries.get(i).findElement(By.xpath("//td[5]")).getText());

            System.out.println(countries.get(i).findElement(By.xpath("//td[5]")).getText());

//            try {
//                if (Integer.parseInt(countries.get(i).findElement(By.xpath("//td[3]")).getText()) > 0) {
//                    multiZoneCountry.add(countries.get(i).findElement(By.xpath("a")).getAttribute("href"));
//                }
//            } catch (ClassCastException ex) {
//                System.out.println("Неверные данные в поле - error");
//            }
        }

        Collections.sort(sortedCountriesList);
        ComparisonStringLists(unsortedCountriesList, sortedCountriesList);

        for (int i = 1; i < multiZoneCountry.size(); i++) {
            wd.get(multiZoneCountry.get(i));

            ArrayList<String> unsortedZonesList = new ArrayList<>();
            ArrayList<String> sortedZonesList = new ArrayList<>();

            List<WebElement> zones = wd.findElements(By.xpath("//table[@id='table-zones']/tr"));

            for (WebElement zone : zones) {
                unsortedZonesList.add(zone.findElement(By.xpath("//td[3]")).getText());
                sortedZonesList.add(zone.findElement(By.xpath("//td[3]")).getText());
            }

            Collections.sort(sortedZonesList);
            ComparisonStringLists(unsortedZonesList, sortedZonesList);

        }
    }

    void ComparisonStringLists(ArrayList<String> unsortedList, ArrayList<String> sortedList) {
        for (int i = 0; i < unsortedList.size(); i++) {
            if (unsortedList.get(i).equals(sortedList.get(i))) {
                System.out.println(unsortedList.get(i) + " == " + sortedList.get(i));
            } else {
                System.out.println("    |-  " + unsortedList.get(i) + " != " + sortedList.get(i));
                System.out.println("Сортировка на странице не верна");
            }
        }
    }
}
