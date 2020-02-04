package com.vivacom.demo.gui.pages.LoginPageSB;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class BanitsaOnBites extends AbstractPage {
    public BanitsaOnBites(WebDriver driver) {
        super(driver);
    }

    //a[@data-id='8']
    @FindBy(xpath = "/html/body/main/div/div[1]/section/div/article[2]/a")
    private ExtendedWebElement takeBanitsaPaio90Btn;

    @FindBy(css = "[data-id= '21']")
    private ExtendedWebElement takeBanitsaWithBaconAndCheeseBtn;

    @FindBy(xpath = "/html/body/header/div/a[1]")
    private ExtendedWebElement ordersPage;



    public OrdersPage openOrdersPage(){
        takeBanitsaPaio90Btn.click();
        takeBanitsaWithBaconAndCheeseBtn.click();
        ordersPage.click();
        return new OrdersPage(getDriver());
    }


}

