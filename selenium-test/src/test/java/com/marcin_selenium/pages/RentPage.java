package com.marcin_selenium.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RentPage extends TitlePage{

    @FindBy(xpath = "*//div[@id='rents']/div/h2")
    private WebElement rentPageHeading;

    @FindBy(xpath = "*//div[@id='rents']/div/button")
    private WebElement returnButton;

    public RentPage(WebDriver driver) {
        super(driver);
    }

    public void userShouldOpenRentPageAndSeeHeader_RentsHistory() {
        wait.until(ExpectedConditions.visibilityOf(rentPageHeading));
        String expectedHeading = "RENTS HISTORY";
        String actualHeading = rentPageHeading.getText();
        Assertions.assertEquals(expectedHeading, actualHeading);
    }

    public void clickReturnButton() {
        wait.until(ExpectedConditions.visibilityOf(returnButton));
        returnButton.click();
    }
}
