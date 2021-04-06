package com.marcin_selenium.tests;

import com.marcin_selenium.config.TestConfig;
import com.marcin_selenium.pages.ItemsPage;
import com.marcin_selenium.pages.LoginPage;
import com.marcin_selenium.pages.RentPage;
import com.marcin_selenium.pages.TitlePage;
import org.junit.jupiter.api.Test;

public class ItemPageTestSuite extends TestConfig {

    @Test
    public void userShouldAddedNewItems() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();

        TitlePage titlePage = new TitlePage(driver);
        titlePage.addNewBook();
        titlePage.addNewBook();
        titlePage.clickShowCopiesButton();
        ItemsPage itemsPage = new ItemsPage(driver);
        itemsPage.addNewItem();
        itemsPage.addNewItem();
        itemsPage.compareAddedItems();

        itemsPage.removeItems();
        itemsPage.clickReturnButton();
        titlePage.removeBooks();
    }

    @Test
    public void userShouldSeeEditedCopies() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.addNewBook();
        titlePage.clickShowCopiesButton();
        ItemsPage itemsPage = new ItemsPage(driver);
        itemsPage.addNewItem();
        itemsPage.addNewItem();
        itemsPage.editExistingCopies();

        itemsPage.compareEditedItems();

        itemsPage.removeItems();
        itemsPage.clickReturnButton();
        titlePage.removeBooks();
    }

    @Test
    public void userShouldSeeRentPageOpen() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.addNewBook();
        titlePage.clickShowCopiesButton();
        ItemsPage itemsPage = new ItemsPage(driver);
        itemsPage.addNewItem();
        itemsPage.clickShowCopiesButton();
        RentPage rentPage = new RentPage(driver);
        rentPage.userShouldOpenRentPageAndSeeHeader_RentsHistory();

        rentPage.clickReturnButton();
        itemsPage.removeItems();
        itemsPage.clickReturnButton();
        titlePage.removeBooks();

    }
}
