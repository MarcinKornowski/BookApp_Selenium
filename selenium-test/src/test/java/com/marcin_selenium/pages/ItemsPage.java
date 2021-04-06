package com.marcin_selenium.pages;

import com.marcin_selenium.utility.AppCalendar;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ItemsPage extends TitlePage {

    AppCalendar appCalendar = new AppCalendar(driver);

    @FindBy(xpath = "*//div[@id='title-copies']/div/h2")
    private WebElement itemsPageHeader;

    @FindBy(xpath = "*//li[contains(@id, 'item-')]/div[1]")
    private WebElement itemHeader;
    private By itemHeaderBy = By.xpath("*//li[contains(@id, 'item-')]/div[1]");
    @FindBy(xpath = "//ul/li/div[1]/div[2]")
    private List<WebElement> itemHeadersList;

    @FindBy(xpath = "*//div[@id='title-copies']/button[@id='add-item-button']")
    private WebElement addNewItemButton;

    @FindBy(xpath = "*//li[contains(@id, 'item-')]/div[2]/a")
    private WebElement showCopiesButton;

    @FindBy(xpath = "*//li[contains(@id, 'item-')]/div[2]/button[1]")
    private WebElement editButton;
    private By editButtonBy = By.xpath("*//li[contains(@id, 'item-')]/div[2]/button[1]");
    @FindAll(@FindBy(xpath = "*//li[contains(@id, 'item-')]/div[2]/button[1]"))
    private List<WebElement> editButtons;

    @FindBy(xpath = "*//button[contains(@name, 'submit-button')]")
    private WebElement addNewItemFormButton;

    @FindBy(xpath = "*//div[@id='title-copies']/div/button")
    private WebElement returnButton;

    //date input
    @FindBy(xpath = "*//form/div/div/div/div/input[contains(@name, 'purchase-date')]")
    private WebElement purchaseDateInput;

    //-- CALENDAR - 1;
    @FindBy(xpath = "*//form/div[1]/div/div/div[4]/header/span[1]")
    private WebElement prevButtonCalendarOne;
    @FindBy(xpath = "*//form/div[1]/div/div/div[2]/header/span[2]")
    private WebElement calendarOneHeader;
    @FindBy(xpath = "*//form/div[1]/div/div/div[4]/header/span[3]")
    private WebElement nextButtonCalendarOne;
    @FindAll(@FindBy(xpath = "//form/div[1]/div/div/div[4]/header/span[2]"))
    private List<WebElement> calendarOneHeadersList;

    @FindAll(@FindBy(xpath = "*//form/div[1]/div/div/div[2]/div/span[contains(@class, 'day')]"))
    private List<WebElement> calendarOneDaysList;
    @FindAll(@FindBy(xpath = "*//form/div[1]/div/div/div[3]/span[contains(@class, 'month')]"))
    private List<WebElement> calendarOneMonthsList;
    @FindAll(@FindBy(xpath = "*//form/div[1]/div/div/div[4]/span[contains(@class, 'year')]"))
    private List<WebElement> calendarOneYearList;

    // -- rent data
    private String rentDay = "30";
    private String rentMonth = "December";
    private String rentYear = "2019";

    private String rentDayEdit = "25";
    private String rentMonthEdit = "January";
    private String rentYearEdit = "2018";




    @FindAll(@FindBy(xpath = "*//li[contains(@id, 'item-')]/div[2]/button[2]"))
    private List<WebElement> removeButtons;
    private By removeButtonBy = By.xpath("*//li[contains(@id, 'item-')]/div[2]/button[2]");


    public ItemsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        wait.until(ExpectedConditions.visibilityOf(itemsPageHeader));
        return itemsPageHeader.getText();
    }

    public void clickAddNewItemButton() {
        waitForElementToBePresent(By.xpath("*//div[@id='title-copies']/button[@id='add-item-button']"));
        waitForVisibility(By.xpath("*//div[@id='title-copies']/button[@id='add-item-button']"));
        wait.until(ExpectedConditions.elementToBeClickable(addNewItemButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(addNewItemButton).click().build().perform();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//div[@id='title-copies']"))));
    }

    public void clickShowCopiesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(showCopiesButton));
        showCopiesButton.click();
    }

    public void clickPurchaseDateInput() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseDateInput));
        purchaseDateInput.click();
    }

    public void clickAddNewItemFormButton() {
        wait.until(ExpectedConditions.visibilityOf(addNewItemFormButton));
        addNewItemFormButton.click();
    }

    public void clickReturnButton() {
        wait.until(ExpectedConditions.elementToBeClickable(returnButton));
        returnButton.click();
    }

    private void searchCalendar() {
        clickPurchaseDateInput();
        appCalendar.clickCalendarYear(calendarOneHeader);
        appCalendar.getDate(calendarOneHeadersList,
                calendarOneYearList,
                calendarOneMonthsList,
                calendarOneDaysList,
                rentYear,
                rentMonth,
                rentDay,
                prevButtonCalendarOne,
                nextButtonCalendarOne);
    }

    private void searchCalendarToEdit() {
        clickPurchaseDateInput();
        appCalendar.clickCalendarYear(calendarOneHeader);
        appCalendar.getDate(calendarOneHeadersList,
                calendarOneYearList,
                calendarOneMonthsList,
                calendarOneDaysList,
                rentYearEdit,
                rentMonthEdit,
                rentDayEdit,
                prevButtonCalendarOne,
                nextButtonCalendarOne);
    }

    public void addNewItem() {
        clickAddNewItemButton();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//form[contains(@class, 'item-form')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//form[contains(@class, 'item-form')]")));
        searchCalendar();
        clickAddNewItemFormButton();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("*//ul[contains(@class, 'items-list')]"))));
    }

    //EDIT ----------------------------------------------------------------------------
    public void editExistingCopies() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(editButtonBy));
        for(WebElement editButton : editButtons) {
            wait.until(ExpectedConditions.visibilityOf(editButton));
            wait.until(ExpectedConditions.elementToBeClickable(editButton));
            Actions actions = new Actions(driver);
            actions.moveToElement(editButton).click().build().perform();
            waitForElementToBePresent(By.xpath("*//form[contains(@class, 'item-form')]"));
            searchCalendarToEdit();
            clickAddNewItemFormButton();
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(itemHeaderBy)));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(editButtons)));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//div[@id='title-copies']"))));
        }
    }

    //- REMOVE -----------------------------------------------------------------
    public void removeItems() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("*//li[contains(@id, 'item-')]/div[2]/button[2]")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(removeButtonBy));
        for(WebElement removeButton : removeButtons) {
            wait.until(ExpectedConditions.visibilityOf(removeButton ));
            wait.until(ExpectedConditions.elementToBeClickable(removeButton));
            createAction().moveToElement(removeButton).click().build().perform();
            wait.until(ExpectedConditions.stalenessOf(removeButton));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//div[@id='title-copies']"))));
        }
    }// ------------------------------------------------------------------------


    public List<String> getActualHeadersData(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    private String getFormattedDates(String year, String month, String day) {
        String joinedDate = year+ " " + month + " " + day;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM d", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(joinedDate, formatter);
        return date.toString();
    }

    //--- ASSERTION -------------------------
    public void itemsPageShouldBeOpen() {
        String actualHeader = getPageHeader();
        String expectedHeader = "LIST OF COPIES";
        Assertions.assertEquals(expectedHeader, actualHeader);
    }

    public void compareAddedItems() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemHeadersList));
        List<String> actualItemsAdded = getActualHeadersData(itemHeadersList);

        String purchaseDate = getFormattedDates(rentYear, rentMonth, rentDay);
        List<String> expectedItemsAdded = new ArrayList<>();
        expectedItemsAdded.add(purchaseDate);
        expectedItemsAdded.add(purchaseDate);

        Assertions.assertEquals(expectedItemsAdded, actualItemsAdded);
        System.out.println(actualItemsAdded);
        System.out.println(expectedItemsAdded);
    }

    public void compareEditedItems() {
        wait.until(ExpectedConditions.visibilityOfAllElements(itemHeadersList));
        List<String> actualItemsAdded = getActualHeadersData(itemHeadersList);

        String purchaseDate = getFormattedDates(rentYearEdit, rentMonthEdit, rentDayEdit);
        List<String> expectedItemsAdded = new ArrayList<>();
        expectedItemsAdded.add(purchaseDate);
        expectedItemsAdded.add(purchaseDate);

        Assertions.assertEquals(expectedItemsAdded, actualItemsAdded);
        System.out.println(actualItemsAdded);
        System.out.println(expectedItemsAdded);
    }
}
