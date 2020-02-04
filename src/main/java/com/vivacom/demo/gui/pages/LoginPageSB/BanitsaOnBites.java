package com.vivacom.demo.gui.pages.LoginPageSB;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    @FindBy(css = "div.content>article")
    private List<ExtendedWebElement> existingProducts;

    @FindBy(xpath = "//a[@class='cart']")
    private ExtendedWebElement shoppingCartBtn;


    public OrdersPage clickShoppingCartButton(){
        shoppingCartBtn.click();
        return new OrdersPage(getDriver());
    }


    public void addProductsToCart(List<String> productsToOrder) {

        for (String productToOrder : productsToOrder) {
            for(ExtendedWebElement existingProduct : existingProducts){
                if(productToOrder.equals(existingProduct.findExtendedWebElement(By.cssSelector("h2")).getText())){
                    existingProduct.findExtendedWebElement(By.cssSelector("a.add-to-cart")).click();
                }
            }

        }

    }


}

