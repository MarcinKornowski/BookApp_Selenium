package com.marcin_selenium.tests;

import com.marcin_selenium.config.TestConfig;
import com.marcin_selenium.pages.LoginPage;
import com.marcin_selenium.pages.TitlePage;
import org.junit.jupiter.api.Test;

public class LoginPageTestSuite extends TestConfig {

    @Test
    public void loginUser_WithCorrectData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithCorrectData();
        TitlePage titlePage = new TitlePage(driver);
        titlePage.userShouldBeLoggedToApp();
    }

    @Test
    public void loginUser_WithoutLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithoutLogin();
        loginPage.userShouldSeeAlert_CanNotLeaveThisFieldEmpty();
    }

    @Test
    public void loginUser_WithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithoutPassword();
        loginPage.userShouldSeeAlert_CanNotLeaveThisFieldEmpty();
    }

    @Test
    public void loginUser_WithWrongLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithWrongLogin();
        loginPage.userShouldSeeAlert_LoginFailed();
    }

    @Test
    public void loginUser_WithWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithWrongPassword();
        loginPage.userShouldSeeAlert_LoginFailed();
    }

    @Test
    public void loginUserWithBothInputFieldsEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser_WithEmptyLoginAndPasswordInput();
        loginPage.userShouldSeeAlert_CanNotLeaveThisFieldEmpty();
    }

    @Test
    public void registerUser_WithCorrectData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registerUserWithCorrectData();
        loginPage.userShouldSeeAlert_SuccessfullyRegistered();
    }

    @Test
    public void userShouldTryToRegister_WithIncorrectPasswordRepeated() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registerUserWith_IncorrectPasswordRepeated();
        loginPage.userShouldSeeAlert_PasswordDoNotMatch();
    }

    @Test
    public void userShouldTryToRegister_WithOutLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registerUser_WithNoLogin();
        loginPage.userShouldSeeAlert_CanNotLeaveThisFieldEmpty();
    }

    @Test
    public void userShouldTryToRegister_WithOutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registerUser_WithNoPassword();
        loginPage.userShouldSeeAlert_CanNotLeaveThisFieldEmpty();
    }

    @Test
    public void userShouldTryToRegister_WithOutPasswordRepeated() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registerUser_WithNoPasswordRepeated();
        loginPage.userShouldSeeAlert_CanNotLeaveThisFieldEmpty();
    }
}
