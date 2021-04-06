package com.marcin_selenium.utility;

import com.github.javafaker.Faker;

public class DataFaker {

    private Faker faker;

    public DataFaker() {
        faker = new Faker();
    }

    public String getFakeName() {
        return faker.name().firstName();
    }

    public String getFakeLastName() {
        return faker.name().lastName();
    }

    public String getFakePassword() {
        return faker.internet().password();
    }
}
