package com.marcin_selenium.pages;

import com.marcin_selenium.config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TitlePage extends TestConfig {

    @FindBy(xpath = "*//div[@id='titles']/button[@id='add-title-button']")
    private WebElement addNewBookButton;
    private By addNewBookBy= By.xpath("*//div[@id='titles']/button[@id='add-title-button']");

    //FORM INPUTS
    @FindBy(xpath = "*//form[contains(@class, 'title-form')]/div[1]/label/input[contains(@name, 'title')]")
    private WebElement titleInput;
    private By titleInputBy = By.xpath("*//form[contains(@class, 'title-form')]/div[1]/label/input[contains(@name, 'title')]");

    @FindBy(xpath = "*//form[contains(@class, 'title-form')]/div[2]/label/input[contains(@name, 'author')]")
    private WebElement authorInput;
    private By authorInputBy = By.xpath("*//form[contains(@class, 'title-form')]/div[2]/label/input[contains(@name, 'author')]");

    @FindBy(xpath = "*//form[contains(@class, 'title-form')]/div[3]/label/input[contains(@name,'year')]")
    private WebElement yearInput;
    private By yearInputBy = By.xpath("*//form[contains(@class, 'title-form')]/div[3]/label/input[contains(@name,'year')]");

    //FORM BUTTON
    @FindBy(xpath = "*//form[contains(@class, 'title-form')]/button[contains(@name, 'submit-button')]")
    private WebElement addTitleButton;

    //list buttons
    @FindBy(xpath = "*//li[contains(@id, 'title-')]/div[2]/a")
    private WebElement showCopiesButton;

    @FindBy(xpath = "*//li[contains(@id, 'title-')]/div[2]/button[1]")
    private WebElement editButton;
    private By editButtonBy = By.xpath("*//li[contains(@id, 'title-')]/div[2]/button[1]");
    @FindAll(@FindBy(xpath = "*//li[contains(@id, 'title-')]/div[2]/button[1]"))
    private List<WebElement> editButtons;

    @FindBy(xpath = "*//li[contains(@id, 'title-')]/div[2]/button[2]")
    private WebElement removeButton;
    @FindAll(@FindBy(xpath = "*//li[contains(@id, 'title-')]/div[2]/button[2]"))
    private List<WebElement> removeButtons;
    private By removeButtonBy = By.xpath("*//li[contains(@id, 'title-')]/div[2]/button[2]");

    //li header
    @FindBy(xpath = "*//li[contains(@id, 'title-')]/div[1]")
    private WebElement titleHeader;
    private By titleHeaderBy = By.xpath("*//li[contains(@id, 'title-')]/div[1]");
    @FindBy(xpath = "//ul/li/div/div[1][contains(@class, 'titles-list__item__title')]")
    private WebElement addedHeaderTitle;

    @FindBy(xpath = "//ul/li/div/div[2][contains(@class, 'titles-list__item__author')]")
    private WebElement addedHeaderAuthor;

    @FindBy(xpath = "//ul/li/div/div[3][contains(@class, 'titles-list__item__year')]")
    private WebElement addedHeaderYear;

    @FindAll(@FindBy(xpath = "*//ul[contains(@class, 'titles-list')]/li[contains(@id, 'title-')]/div[1]"))
    private List<WebElement> bookList;

    @FindBy(xpath = "*//div[contains(@class, 'full-screen')]/a")
    private WebElement closeFullScreenButton;

    @FindBy(xpath = "*//div[contains(@class, 'alert--error')]")
    private WebElement alertError;

    @FindBy(xpath = "//*[@id=\"titles\"]/h2")
    private WebElement pageHeading; // = driver.findElement(By.xpath("//*[@id=\"titles\"]/h2"));

    public TitlePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void waitForVisibility(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void waitForElementToBeClickable(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitForElementToBePresent(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private String bookTitle = "Book1";
    private String bookAuthor = "Author1";
    private String bookYear = "1000";

    private String bookTitle2 = "BookOne";
    private String bookAuthor2 = "AuthorOne";
    private String bookYear2 = "2000";

    WebDriverWait wait = new WebDriverWait(driver, 5);

    public void clickAddNewBookButton() {
        waitForElementToBePresent(By.xpath("*//div[@id='titles']/button[@id='add-title-button']"));
        waitForVisibility(By.xpath("*//div[@id='titles']/button[@id='add-title-button']"));
        waitForElementToBeClickable(By.xpath("*//div[@id='titles']/button[@id='add-title-button']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(addNewBookButton).click().build().perform();
    }

    public void clickShowCopiesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(showCopiesButton));
        showCopiesButton.click();
    }

    public void clickEditButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editButton.click();
    }

    public void clickRemoveButton() {
        wait.until(ExpectedConditions.visibilityOf(removeButton));
        removeButton.click();
    }

    public void clickCloseFullscreenButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeFullScreenButton));
        closeFullScreenButton.click();
    }

    public void enterTitle(String title) {
        titleInput.sendKeys(title);
    }

    public void enterAuthor(String author) {
        authorInput.sendKeys(author);
    }

    public void enterYear(String year) {
        yearInput.sendKeys(year);
    }

    public void clickAddTitle() {
        addTitleButton.click();
    }

    // --ADD BOOKS --------------------------------------------------------------------
    public void addNewBook() {
        clickAddNewBookButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//form[contains(@class, 'title-form')]")));
        enterTitle(bookTitle);
        enterAuthor(bookAuthor);
        enterYear(bookYear);
        clickAddTitle();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("*//ul[contains(@class, 'titles-list')]"))));
    }

    public void addBook_WithoutTitle() {
        clickAddNewBookButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//form[contains(@class, 'title-form')]")));
        enterAuthor(bookAuthor);
        enterYear(bookYear);
        clickAddTitle();
    }
    public void addBook_WithoutAuthor() {
        clickAddNewBookButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//form[contains(@class, 'title-form')]")));
        enterTitle(bookTitle);
        enterYear(bookYear);
        clickAddTitle();
    }
    public void addBook_WithoutYear() {
        clickAddNewBookButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//form[contains(@class, 'title-form')]")));
        enterTitle(bookTitle);
        enterAuthor(bookAuthor);
        clickAddTitle();
    }




    //EDIT ----------------------------------------------------------------------------
    public void editExistingBooks() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editButtonBy));
        for(WebElement editButton : editButtons) {
            wait.until(ExpectedConditions.visibilityOf(editButton));
            wait.until(ExpectedConditions.elementToBeClickable(editButton));
            Thread.sleep(500);
            Actions actions = new Actions(driver);
            actions.moveToElement(editButton).click().build().perform();
            Thread.sleep(500);
            waitForElementToBePresent(By.xpath("*//form[contains(@class, 'title-form')]"));

            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(titleInputBy))).clear();
            enterTitle(bookTitle2);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(authorInputBy))).clear();
            enterAuthor(bookAuthor2);
            wait.until(ExpectedConditions.visibilityOfElementLocated(yearInputBy)).clear();
            enterYear(bookYear2);
            clickAddTitle();
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(titleHeaderBy)));
            Thread.sleep(500);
        }
    }

    private void editBook_NoData(WebElement headerElement, WebElement input) {
        clickEditButton();
        String[] inputText = headerElement.getText().split("");
        for(String s : inputText) {
            input.sendKeys(Keys.BACK_SPACE);
        }
        clickAddTitle();
    }

    public void editBook_WithoutTitle() {
        editBook_NoData(addedHeaderTitle, titleInput);
    }
    public void editBook_WithoutAuthor() {
        editBook_NoData(addedHeaderAuthor, authorInput);
    }
    public void editBook_WithoutYear() {
        editBook_NoData(addedHeaderYear, yearInput);
    }// EDIT END ------------------------------------------------------------

    //- REMOVE -----------------------------------------------------------------
    public void removeBooks() {
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("*//li[contains(@id, 'title-')]/div[2]/button[2]")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(removeButtonBy));
        //List<WebElement> removeButtons = driver.findElements(By.xpath("*//li[contains(@id, 'title-')]/div[2]/button[2]"));
        for(WebElement removeButton : removeButtons) {
            wait.until(ExpectedConditions.visibilityOf(removeButton ));
            wait.until(ExpectedConditions.elementToBeClickable(removeButton));
            Actions actions = new Actions(driver);
            actions.moveToElement(removeButton ).click().build().perform();
            wait.until(ExpectedConditions.stalenessOf(removeButton));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//div[@id='titles']"))));
        }
    }// ------------------------------------------------------------------------

    // ---------- ASSERTIONS --------------------
    private List<String> getBooksHeaders() {
        List<String> bookListHeaders = new ArrayList<>();
        for(WebElement book : bookList) {
            bookListHeaders.add(book.getText());
        }
        return bookListHeaders;
    }

    public void userShouldBeLoggedToApp() {
        wait.until(ExpectedConditions.visibilityOf(pageHeading));
        String expectedHeading = "TITLES CATALOG";
        String actualHeading = pageHeading.getText();
        Assertions.assertEquals(expectedHeading, actualHeading);
    }

    public void compareAddedBooks() {
        wait.until(ExpectedConditions.visibilityOfAllElements(bookList));
        List<String> actualList = getBooksHeaders();
        List<String> expectedList = new ArrayList<>();
        expectedList.add(bookTitle.toUpperCase() + "\n" + "by " + bookAuthor + "\n" + "(" + bookYear + ")");
        expectedList.add(bookTitle.toUpperCase() + "\n" + "by " + bookAuthor + "\n" + "(" + bookYear + ")");
        Assertions.assertEquals(expectedList, actualList);
        System.out.println("Expected: " + expectedList);
        System.out.println("Actual: " + actualList);
    }

    public void compareEditedBooks() {
        wait.until(ExpectedConditions.visibilityOfAllElements(bookList));
        List<String> actualList = getBooksHeaders();
        List<String> expectedList = new ArrayList<>();
        expectedList.add(bookTitle2.toUpperCase() + "\n" + "by " + bookAuthor2 + "\n" + "(" + bookYear2 + ")");
        expectedList.add(bookTitle2.toUpperCase() + "\n" + "by " + bookAuthor2 + "\n" + "(" + bookYear2 + ")");
        Assertions.assertEquals(expectedList, actualList);
        System.out.println("Expected: " + expectedList);
        System.out.println("Actual: " + actualList);
    }

    private void userAlert(String expectedAlert) {
        wait.until(ExpectedConditions.visibilityOf(alertError));
        String actualAlert = alertError.getText();
        Assertions.assertEquals(expectedAlert, actualAlert);
    }

    public void userShouldSeeAlert_titleFieldShouldNotBeEmpty() {
        userAlert("\"title\" field shouldn't be empty...");
    }

    public void userShouldSeeAlert_authorFieldShouldNotBeEmpty() {
        userAlert("\"author\" field shouldn't be empty...");
    }

    public void userShouldSeeAlert_yearFieldShouldNotBeEmpty() {
        userAlert("\"year\" field shouldn't be empty...");
    }

    public void userShouldSeeAlert_CanNotRemoveTitlesWithCopies() {
        userAlert("You can't remove titles with copies!");
    }// ---------- ASSERTIONS END -----------------------------
}
