package ru.stqa.selenium_training.tests.ui;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckSort extends BaseTests {

    @Test
    public void CheckSortCountries() {
        TestLogon.logonAdmin();
        wd.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        ArrayList<String> unsortedCountriesList = new ArrayList<>();
        ArrayList<String> sortedCountriesList = new ArrayList<>();

        ArrayList<String> multiZoneCountry = new ArrayList<>();

        List<WebElement> countries = wd.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']"));

        System.out.println("Найдено " + countries.size() + " стран");

        for (int i = 0; i < countries.size(); i++) {
            //System.out.println(countries.get(i).findElement(By.xpath(".//td[5]")).getText());

            unsortedCountriesList.add(countries.get(i).findElement(By.xpath(".//td[5]")).getText());
            sortedCountriesList.add(countries.get(i).findElement(By.xpath(".//td[5]")).getText());

            try {
                //System.out.println(countries.get(i).findElement(By.xpath(".//td[6]")).getText());

                if (Integer.parseInt(countries.get(i).findElement(By.xpath(".//td[6]")).getText()) > 0) {
                    System.out.println(countries.get(i).findElement(By.xpath(".//a")).getAttribute("href"));

                    multiZoneCountry.add(countries.get(i).findElement(By.xpath(".//a")).getAttribute("href"));
                }
            } catch (ClassCastException ex) {
                System.out.println("Неверные данные в поле - error");
            }
        }

        Collections.sort(sortedCountriesList);
        ComparisonStringLists(unsortedCountriesList, sortedCountriesList);

        for (int i = 0; i < multiZoneCountry.size(); i++) {
            wd.get(multiZoneCountry.get(i));

            ArrayList<String> unsortedZonesList = new ArrayList<>();
            ArrayList<String> sortedZonesList = new ArrayList<>();

            List<WebElement> zones = wd.findElements(By.xpath("//table[@id='table-zones']//tr"));

            System.out.println("Найдено " + zones.size() + " зон");

            for (int ii = 1; ii < zones.size() - 1; ii++) {
                //System.out.println(zones.get(ii).findElement(By.xpath(".//td[3]")).getText());

                unsortedZonesList.add(zones.get(ii).findElement(By.xpath(".//td[3]")).getText());
                sortedZonesList.add(zones.get(ii).findElement(By.xpath(".//td[3]")).getText());
            }

            ComparisonStringLists(unsortedZonesList, sortedZonesList);

        }
    }

    @Test
    public void CheckSortZones() {
        TestLogon.logonAdmin();
        wd.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        ArrayList<String> unsortedCountriesList = new ArrayList<>();
        ArrayList<String> sortedCountriesList = new ArrayList<>();

        ArrayList<String> multiZoneCountry = new ArrayList<>();

        List<WebElement> countries = wd.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']"));

        System.out.println(countries.size());

        for (int i = 0; i < countries.size(); i++) {
            System.out.println(countries.get(i).findElement(By.xpath(".//td[3]")).getText());

            unsortedCountriesList.add(countries.get(i).findElement(By.xpath(".//td[3]")).getText());
            sortedCountriesList.add(countries.get(i).findElement(By.xpath(".//td[3]")).getText());

            try {
                if (Integer.parseInt(countries.get(i).findElement(By.xpath(".//td[4]")).getText()) > 0) {
                    System.out.println(countries.get(i).findElement(By.xpath(".//a")).getAttribute("href"));

                    multiZoneCountry.add(countries.get(i).findElement(By.xpath(".//a")).getAttribute("href"));
                }
            } catch (ClassCastException ex) {
                System.out.println("Неверные данные в поле - error");
            }
        }

        for (int i = 0; i < multiZoneCountry.size(); i++) {
            wd.get(multiZoneCountry.get(i));

            ArrayList<String> unsortedZonesList = new ArrayList<>();
            ArrayList<String> sortedZonesList = new ArrayList<>();

            List<WebElement> zones = wd.findElements(By.xpath("//table[@id='table-zones']//tr"));

            System.out.println(zones.size());

            for (int ii = 1; ii < zones.size() - 1; ii++) {
                System.out.println(zones.get(ii).findElement(By.xpath(".//td[3]/select/option[@selected='selected']")).getText());

                unsortedZonesList.add(zones.get(ii).findElement(By.xpath(".//td[3]/select/option[@selected='selected']")).getText());
                sortedZonesList.add(zones.get(ii).findElement(By.xpath(".//td[3]/select/option[@selected='selected']")).getText());
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
