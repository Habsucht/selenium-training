package ru.stqa.selenium_training.data;

/**
 *  Class data generation for user
 */

import java.util.Random;

public class User {
    private static String[] COMPANY_NAME = {"Facebook", "Yandex", "VK", "Google", "Rambler", "Sun", "Oracle", "Apple", "Cisco", "DLink"};
    private static String[] FIRST_NAME = {"Liam", "Noah", "Mason", "Ethan", "Logan", "Lucas", "Jackson", "Aiden", "Oliver",
            "Jacob", "Elijah", "Alexander", "James", "Benjamin", "Jack", "Luke", "William", "Michael", "Owen", "Daniel", "Carter",
            "Gabriel", "Henry", "Matthew", "Wyatt", "Caleb", "Jayden", "Nathan", "Ryan", "Isaac"};
    private static String[] LAST_NAME = {"Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Wood", "Lewis", "Scott", "Cooper", "King",
            "Green", "Walker", "Edwards", "Turner", "Morgan", "Baker", "Hill", "Phillips"};
    private static String[] CITY_NAME = {"Moscow", "NewYork", "SanFrancisco", "Brooklyn", "Paris"};
    private static String[] STREET_NAME = {"prosp. Stroiteley", "ul. Cvetochnaya", "3th Microraion", "Highhill Street", "Woodpark Drive"};
    private static String[] DOMAIN = {".ru", ".com", ".pw", ".net", ".free", ".int"};

    private String tax_id;
    private String companyName;
    private String firstname;
    private String lastname;

    private String postcode;
    private String city;
    private String address1;
    private String address2;

    private String email;
    private String phone;
    private String password;

    public User() {
        this.tax_id = String.valueOf(generateRandom(100000000));
        this.companyName = COMPANY_NAME[generateRandom(COMPANY_NAME.length)];
        this.firstname = FIRST_NAME[generateRandom(FIRST_NAME.length)];
        this.lastname = LAST_NAME[generateRandom(LAST_NAME.length)];

        this.postcode = generateRandomStringNumber(5);
        this.city = CITY_NAME[generateRandom(CITY_NAME.length)];
        this.address1 = city + ", " + STREET_NAME[generateRandom(STREET_NAME.length)] + " " + generateRandom(100);
        this.address2 = "";

        this.email = this.firstname + "." + this.lastname + "@" + this.companyName + DOMAIN[generateRandom(DOMAIN.length)];
        this.phone = "89" + generateRandomStringNumber(9);
        this.password = this.firstname + generateRandom(100);
    }


    int generateRandom(int n) {
        Random random = new Random();
        return Math.abs(random.nextInt()) % n;
    }

    String generateRandomStringNumber(int n) {
        String numberString = "";
        for (int i = 0; i < n; i++) {
            numberString = numberString + generateRandom(10);
        }
        return numberString;
    }

    public String getTax_id() {
        return tax_id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}

