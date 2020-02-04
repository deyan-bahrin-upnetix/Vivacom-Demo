package com.vivacom.demo.gui.pages.LoginPageSB;

import com.google.errorprone.annotations.FormatMethod;
import com.qaprosoft.carina.core.foundation.utils.android.IAndroidUtils;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import cucumber.api.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class DeliveryDetails extends AbstractPage {
    public DeliveryDetails(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@id='addr_id']")
    private ExtendedWebElement adressDropDownMenu;

    @FindBy(xpath = "//*[@id='client-form']/div[7]/input[1]")
    private ExtendedWebElement clietNeighborhoodBox;

    @FindBy(xpath = "//*[@id='client-form']/div[7]/input[2]")
    private ExtendedWebElement clientAdressBox;

    @FindBy(xpath = "//*[@id='delivery_on']")
    private ExtendedWebElement selectDateAndTime;

    @FindBy(xpath = "//*[@id='client-form']/a/span")
    private ExtendedWebElement finishOrderBtn;

    @FindBy(name = "invoice")
    private ExtendedWebElement invoiceCheckBox;

    @FindBy(css = "td.day")
    private List<ExtendedWebElement> dateElements;


    @FindBy(css = "span.hour")
    private List<ExtendedWebElement> hoursElements;


    @FindBy(css = "span.minute")
    private List<ExtendedWebElement> minutesElements;

    @FindBy(xpath = "//select[@id='addr_id']//option[3]")
    private ExtendedWebElement existingAdressOption;


    /*This static fields are used to create random day, hour and minutes - String date is used in DeliveryDetails class*/
    private static Random random = new Random();
    public static String day = String.valueOf(LocalDate.now().plusDays(1).getDayOfMonth());
    public static String hour = String.valueOf(11 + random.nextInt(3));
    public static String minutes = String.valueOf(5 * random.nextInt(12));
    public static String hourMinutes = hour + ":" + minutes;
    public static String date = String.valueOf(LocalDate.now().plusDays(1) + " " + hourMinutes);


    /*Logic here is to verify that the invoiceCheckBox is not checked and is clickable.*/
    public boolean isInvoiceOk() {
        if (!invoiceCheckBox.isChecked() && invoiceCheckBox.isClickable()) {
            return true;

        }
        return false;
    }


    /*Logic here is first to check that the adress is not existing into the dropdown menu- if exists we select second index. if not we type in Адрес and Квартал.*/
    public void getAdress() {

        if (!existingAdressOption.isElementPresent()) {
            clietNeighborhoodBox.type("Люлин");
            clientAdressBox.type("ул.Борис 3");
        } else {
            adressDropDownMenu.select(2);
        }

    }

    /*We have 3 foreach loops, first is to click on random day, second should click on random hour, and third is to click on hour and minutes */
    public void getDateTimeForShiping() {
        selectDateAndTime.click();

        for (ExtendedWebElement element : dateElements) {
            String elementClass = element.getAttribute("class");
            String elementValue = element.getText();
            if (!elementClass.contains("disabled") & day.equals(elementValue)) {

                element.click();
                break;
            }
        }
        for (ExtendedWebElement element : hoursElements) {
            String elementClass = element.getAttribute("class");
            String elementValue = element.getText();
            if (!elementClass.contains("disabled") & (hour + ":00").equals(elementValue)) {

                element.click();
                break;
            }
        }
        for (ExtendedWebElement element : minutesElements) {
            String elementValue = element.getText();
            if ((hour + ":" + minutes).equals(elementValue)) {
                element.click();
                WebDriverWait wait = new WebDriverWait(driver, 5);
                break;
            }
        }
    }

    public FinishOrder finishTheOrder() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        finishOrderBtn.click();
        return new FinishOrder(getDriver());
    }

}
