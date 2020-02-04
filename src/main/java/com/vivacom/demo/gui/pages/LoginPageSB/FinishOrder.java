package com.vivacom.demo.gui.pages.LoginPageSB;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class FinishOrder extends AbstractPage {
    public FinishOrder(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='checkout']/div/ul/li[1]/a")
    private ExtendedWebElement backToProdcutsBtn;

    @FindBy(className = "client_city")
    private ExtendedWebElement cityText;

    @FindBy(className = "addr_address")
    private ExtendedWebElement adressText;

    @FindBy(className = "delivery_on")
    private ExtendedWebElement dateTimeDeliveryOnText;

    @FindBy(css = "div.table-like")
    private ExtendedWebElement orderTable;

    @FindBy(xpath = "//div[@class='step three']//div[@class='table-like']//div[@class='row c prod']")
    private List<ExtendedWebElement> tableElements;


    /*takeSnapShot is creating a picture and it's saved in C//users/name-of-the-user/Local/Temp,*/
    public void takeSnapShot(WebDriver webdriver) throws Exception {

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        /*First we scroll up to see the productsButton then we click on him*/
        backToProdcutsBtn.scrollTo();
        backToProdcutsBtn.click();
    }

    /*isOrderOk is use to verify that the products are two, and quantity is 1, also first if is checking the city adress and dateAndTime.*/
    public boolean isOrderOk() {
        if (!cityText.getText().equals("София") && !adressText.getText().equals("ул.Борис 3") && !dateTimeDeliveryOnText.getText().equals(DeliveryDetails.date)) {
            return false;
        }

        if (tableElements.size() != 2) {
            return false;
        }
        for (ExtendedWebElement element : tableElements) {
            String elementValue = element.getAttribute("data-prod_id");
            String quantity = element.findExtendedWebElement(By.xpath("//div[@class='step three']//div[2]//span[3]")).getText();

            if (!elementValue.equals("21") && !elementValue.equals("8")) {
                return false;
            }
            if (!quantity.equals("1")) {
                return false;
            }
        }
        return true;
    }
}

