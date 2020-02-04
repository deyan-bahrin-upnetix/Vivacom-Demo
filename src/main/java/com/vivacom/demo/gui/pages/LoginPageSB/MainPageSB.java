package com.vivacom.demo.gui.pages.LoginPageSB;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class MainPageSB extends AbstractPage {



    public MainPageSB(WebDriver driver) {
        super(driver);


    }
    @FindBy(className = "a-like")
    private ExtendedWebElement profile;

    @FindBy(xpath = "/html/body/main/div/div[1]/nav/ul[1]/li[1]/a")
    private ExtendedWebElement baniciBtn;


    /*This method check for Профил btn in mainPage*/
    public boolean selectProfile(){
        return profile.isVisible();

    }

    public Banitsa OpenBanitsaPage(){
        baniciBtn.click();
        return new Banitsa(getDriver());
    }









}
