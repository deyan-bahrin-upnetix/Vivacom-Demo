package com.vivacom.demo.gui.pages.LoginPageSB;

import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageSB extends AbstractPage {

    public LoginPageSB(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "/html/body/header/div/nav/ul/li[2]/a")
    private ExtendedWebElement inButton;


    @FindBy(name = "client_mail")
    private ExtendedWebElement typeEmail;

    @FindBy(name = "client_password")
    private ExtendedWebElement typePassword;

    @FindBy(xpath = "/html/body/main/div/div/form[1]/button/span")
    private ExtendedWebElement loginButton;


    public void setTypeEmail(String email){
        typeEmail.type(email);

    }
    public void setTypePassword(String password){
        typePassword.type(password);
    }

    /*Login logic - get the email and password from config file.*/
    public MainPageSB mainPage() {
        inButton.click();
        String _emailAdress = R.CONFIG.get("v.email");
        String _password = R.CONFIG.get("v.password");
        setTypeEmail(_emailAdress);
        setTypePassword(_password);
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return new MainPageSB(getDriver());
    }

}