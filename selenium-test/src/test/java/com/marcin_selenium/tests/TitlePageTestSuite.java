package com.marcin_selenium.tests;

import com.marcin_selenium.config.TestConfig;
import com.marcin_selenium.pages.ItemsPage;
import com.marcin_selenium.pages.LoginPage;
import com.marcin_selenium.pages.TitlePage;
import org.junit.jupiter.api.Test;

public class TitlePageTestSuite extends TestConfig {

    //    //Add book tests- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - -
    @Test
    public void userShouldAddedNewBooks() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.addNewBook();
        titlePage.addNewBook();
        titlePage.compareAddedBooks();

        titlePage.removeBooks();
    }

    @Test
    public void addBook_WithNoTitle() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.addBook_WithoutTitle();
        titlePage.userShouldSeeAlert_titleFieldShouldNotBeEmpty();

        titlePage.clickCloseFullscreenButton();
    }

    @Test
    public void addBook_WithNoAuthor() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.addBook_WithoutAuthor();
        titlePage.userShouldSeeAlert_authorFieldShouldNotBeEmpty();

        titlePage.clickCloseFullscreenButton();
    }

    @Test
    public void addBook_WithNoYear() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.addBook_WithoutYear();
        titlePage.userShouldSeeAlert_yearFieldShouldNotBeEmpty();

        titlePage.clickCloseFullscreenButton();
    }

    //    //Edit book tests- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - -
    @Test
    public void userShouldEditedBooks() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.addNewBook();
        titlePage.addNewBook();
        titlePage.editExistingBooks();
        titlePage.compareEditedBooks();

        titlePage.removeBooks();
    }
    @Test
    public void editBook_WithRemovedTitleFromForm() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.addNewBook();
        titlePage.editBook_WithoutTitle();
        titlePage.userShouldSeeAlert_titleFieldShouldNotBeEmpty();

        titlePage.clickCloseFullscreenButton();
        titlePage.removeBooks();
    }
    //
    @Test
    public void editBook_WithRemovedAuthorFromForm() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();

        TitlePage titlePage = new TitlePage(driver);
        titlePage.addNewBook();
        titlePage.editBook_WithoutAuthor();

        titlePage.userShouldSeeAlert_authorFieldShouldNotBeEmpty();

        titlePage.clickCloseFullscreenButton();
        titlePage.removeBooks();
    }
    //
    @Test
    public void editBook_WithRemovedYearFromForm() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.addNewBook();
        titlePage.editBook_WithoutYear();

        titlePage.userShouldSeeAlert_yearFieldShouldNotBeEmpty();

        titlePage.clickCloseFullscreenButton();
        titlePage.removeBooks();
    }

    @Test
    public void userShouldSeeItemsPageOpen() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.addNewBook();
        titlePage.clickShowCopiesButton();
        ItemsPage itemsPage = new ItemsPage(driver);

        itemsPage.itemsPageShouldBeOpen();

        itemsPage.clickReturnButton();
        titlePage.removeBooks();
    }

    @Test
    public void userShouldSeeAlert_CanNotRemoveTitlesWithCopies() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.addNewBook();
        titlePage.clickShowCopiesButton();
        ItemsPage itemsPage = new ItemsPage(driver);
        itemsPage.addNewItem();
        itemsPage.addNewItem();
        itemsPage.clickReturnButton();
        titlePage.clickRemoveButton();

        titlePage.userShouldSeeAlert_CanNotRemoveTitlesWithCopies();

        titlePage.clickShowCopiesButton();
        itemsPage.removeItems();
        itemsPage.clickReturnButton();
        titlePage.removeBooks();
    }
}
