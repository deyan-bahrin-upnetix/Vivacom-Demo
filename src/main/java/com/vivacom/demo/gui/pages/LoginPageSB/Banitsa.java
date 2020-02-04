package com.vivacom.demo.gui.pages.LoginPageSB;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Banitsa extends AbstractPage {
    public Banitsa(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "/html/body/main/div/div[1]/nav/ul[2]/li[3]/a")
    private ExtendedWebElement banitsaOnBites;

    @FindBy(xpath = "//a[@class='btn-exit']")
    private ExtendedWebElement exitBtn;

    @FindBy(xpath = "//span[@class='a-like']")
    private ExtendedWebElement profileButton;



    public BanitsaOnBites OpenBnitsaOnBitesPage(){
        banitsaOnBites.click();
        return new BanitsaOnBites(getDriver());
    }

}
