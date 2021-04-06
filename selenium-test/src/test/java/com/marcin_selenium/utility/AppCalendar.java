package com.marcin_selenium.utility;

import com.marcin_selenium.config.TestConfig;
import com.marcin_selenium.pages.TitlePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AppCalendar extends TitlePage {

    WebDriverWait wait = new WebDriverWait(driver, 5);

    public AppCalendar(WebDriver driver) {
        super(driver);
    }

    //CALENDAR
    //chooseYear
    public void chooseYearAll(List<WebElement> allYears, String chooseYear) {
        for(WebElement y : allYears) {
            if(y.getText().equals(chooseYear)) {
                wait.until(ExpectedConditions.visibilityOf(y));
                y.click();
            }
        }
    }

    //choose month
    public void chooseMonthAll(List<WebElement> allMonths, String chooseMonth) {
        for(WebElement m : allMonths) {
            if(chooseMonth.equals(m.getText())) {
                wait.until(ExpectedConditions.visibilityOf(m));
                m.click();
                break;
            }
        }
    }
    //choose day
    public void chooseDayAll(List<WebElement> allDays, String chooseDay) {
        for(WebElement d : allDays) {
            if(chooseDay.equals(d.getText())) {
                wait.until(ExpectedConditions.visibilityOf(d));
                d.click();
                break;
            }
        }
    }


    public void getDate(
            List<WebElement> headers,
            List<WebElement> aYears,
            List<WebElement> aMonths,
            List<WebElement> aDays,
            String year,
            String month,
            String day,
            WebElement prev,
            WebElement next)

    {
        int givenYear = Integer.parseInt(year);

        for(WebElement h : headers) {
            System.out.println(h.getText());
            String[] dat = h.getText().split(" - ");

            int f = Integer.parseInt(dat[0]);
            int l = Integer.parseInt(dat[1]);

            while (givenYear < f) {
                clickPrev(prev);
                f -= 10;
            }
            while (givenYear > l) {
                clickNext(next);
                l += 10;
            }

            chooseYearAll(aYears, year);
            chooseMonthAll(aMonths, month);
            chooseDayAll(aDays, day);
            break;
        }
    }
    public void clickCalendarYear(WebElement yearToChoose){
        Actions builder = new Actions(driver);
        Action doubleClickYear = builder.moveToElement(yearToChoose)
                .doubleClick()
                .build();
        doubleClickYear.perform();
    }
    public void clickPrev(WebElement prevButton) {
        wait.until(ExpectedConditions.visibilityOf(prevButton));
        prevButton.click();
    }
    public void clickNext(WebElement nextButton) {
        wait.until(ExpectedConditions.visibilityOf(nextButton));
        nextButton.click();
    }//CALENDAR END
}
