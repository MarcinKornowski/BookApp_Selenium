package com.marcin_selenium.pages;

import com.marcin_selenium.config.TestConfig;
import com.marcin_selenium.utility.DataFaker;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends TestConfig {

    private static DataFaker dataFaker = new DataFaker();

    //--- inputs
    @FindBy(id = "login")
    private WebElement loginInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "password-repeat")
    private WebElement passwordRepeatInput;

    //--- buttons
    @FindBy(xpath = "//*[@id=\"login-btn\"]")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"register-btn\"]")
    private WebElement registerBtn;

    //---alerts
    @FindBy(xpath = "*//div[contains(@class, 'alert--error')]")
    private WebElement alertError;

    @FindBy(xpath = "*//div[contains(@class, 'alert--success')]")
    private WebElement alertSuccess;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, 5);
    private  String correctLogin = "a b c";
    private String correctPassword = "abc";
    private String wrongLogin = "aaa";
    private String wrongPassword = "bbb";
    private String registerLogin = dataFaker.getFakeName() + "123";


    public void fillLoginInput(String login) {
        wait.until(ExpectedConditions.visibilityOf(loginInput));
        loginInput.sendKeys(login);
    }

    public void fillPasswordInput(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);
    }

    public void fillPasswordRepeatInput(String repeatedPassword) {
        wait.until(ExpectedConditions.visibilityOf(passwordRepeatInput));
        passwordRepeatInput.sendKeys(repeatedPassword);
    }

    public void submitLoginBtn() {
        loginBtn.click();
    }

    public void clickSignUpBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(registerBtn));
        registerBtn.click();
    }

    //--- login methods
    public void loginUser_WithCorrectData() {
        wait.until(ExpectedConditions.visibilityOf(loginInput));
        fillLoginInput(correctLogin);
        fillPasswordInput(correctPassword);
        submitLoginBtn();
    }

    public void loginUser_WithoutPassword() {
        wait.until(ExpectedConditions.visibilityOf(loginInput));
        fillLoginInput(correctLogin);
        submitLoginBtn();
    }

    public void loginUser_WithoutLogin() {
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        fillPasswordInput(correctPassword);
        submitLoginBtn();
    }

    public void loginUser_WithWrongLogin() {
        wait.until(ExpectedConditions.visibilityOf(loginInput));
        fillLoginInput(wrongLogin);
        fillPasswordInput(correctPassword);
        submitLoginBtn();
    }

    public void loginUser_WithWrongPassword() {
        wait.until(ExpectedConditions.visibilityOf(loginInput));
        fillLoginInput(correctLogin);
        fillPasswordInput(wrongPassword);
        submitLoginBtn();
    }

    public void loginUser_WithEmptyLoginAndPasswordInput() {
        wait.until(ExpectedConditions.visibilityOf(loginInput));
        fillLoginInput("");
        fillPasswordInput("");
        submitLoginBtn();
    }//--- login methods end ---------------------------------------------

    //--- register methods
    public void registerUserWithCorrectData() {
        wait.until(ExpectedConditions.visibilityOf(registerBtn));
        clickSignUpBtn();
        wait.until(ExpectedConditions.visibilityOf(passwordRepeatInput));
        fillLoginInput(registerLogin);
        fillPasswordInput("abc");
        fillPasswordRepeatInput("abc");
        clickSignUpBtn();
    }

    public void registerUserWith_IncorrectPasswordRepeated() {
        wait.until(ExpectedConditions.visibilityOf(registerBtn));
        clickSignUpBtn();
        wait.until(ExpectedConditions.visibilityOf(passwordRepeatInput));
        fillLoginInput(registerLogin);
        fillPasswordInput("abc");
        fillPasswordRepeatInput("xyz");
        clickSignUpBtn();
    }

    public void registerUser_WithNoLogin() {
        wait.until(ExpectedConditions.visibilityOf(registerBtn));
        clickSignUpBtn();
        wait.until(ExpectedConditions.visibilityOf(passwordRepeatInput));
        fillPasswordInput(correctPassword);
        fillPasswordRepeatInput(correctPassword);
        clickSignUpBtn();
    }

    public void registerUser_WithNoPassword() {
        wait.until(ExpectedConditions.visibilityOf(registerBtn));
        clickSignUpBtn();
        wait.until(ExpectedConditions.visibilityOf(passwordRepeatInput));
        fillLoginInput(registerLogin);
        fillPasswordRepeatInput("abc");
        clickSignUpBtn();
    }

    public void registerUser_WithNoPasswordRepeated() {
        wait.until(ExpectedConditions.visibilityOf(registerBtn));
        clickSignUpBtn();
        wait.until(ExpectedConditions.visibilityOf(passwordRepeatInput));
        fillLoginInput(registerLogin);
        fillPasswordInput(correctPassword);
        clickSignUpBtn();
    }//--- register methods end ---------------------------------------------



    //--- ASSERTIONS
    public void userShouldSeeAlert_SuccessfullyRegistered() {
        wait.until(ExpectedConditions.visibilityOf(alertSuccess));
        String actualAlert = alertSuccess.getText();
        String expectedAlert = "You have been successfully registered!";
        Assertions.assertEquals(expectedAlert, actualAlert);
    }

    private void userAlertError(String expectedAlert) {
        wait.until(ExpectedConditions.visibilityOf(alertError));
        String actualAlert = alertError.getText();
        Assertions.assertEquals(expectedAlert, actualAlert);
    }

    public void userShouldSeeAlert_CanNotLeaveThisFieldEmpty() {
        userAlertError("You can't leave fields empty");
    }

    public void userShouldSeeAlert_LoginFailed() {
        userAlertError("Login failed");
    }

    public void userShouldSeeAlert_PasswordDoNotMatch() {
        userAlertError("The passwords don't match");
    }
}
