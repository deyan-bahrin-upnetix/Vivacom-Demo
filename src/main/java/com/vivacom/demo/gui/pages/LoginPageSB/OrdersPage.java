package com.vivacom.demo.gui.pages.LoginPageSB;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersPage extends AbstractPage {
    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='checkout']/div/div/div[1]/form/a/span")
    private ExtendedWebElement ordersDetailsBtn;

    @FindBy(xpath = "//*[@id='checkout']/div/div/div[1]/form/div[2]/div[5]/span[1]/a")
    private ExtendedWebElement orderIsEmptyBtn;

    @FindBy(xpath = "//*[@id='checkout']/div/div/div[1]/form/div[2]/div[2]/span[2]/a")
    private ExtendedWebElement selectFirstProduct;

    @FindBy(xpath = "//*[@id='checkout']/div/div/div[1]/form/div[2]/div[3]/span[2]/a")
    private ExtendedWebElement selectSecondProduct;

    @FindBy(name = "prod_quantity[21]")
    private ExtendedWebElement quantityFirstProduct;

    @FindBy(name = "prod_quantity[8]")
    private ExtendedWebElement quantitySecondProduct;

    @FindBy(css = "div.step.one div.row.c.prod")
    private List<ExtendedWebElement> productsInCartStepOne;


    public void deleteTheOrder() {
        orderIsEmptyBtn.click(10);

    }

    public DeliveryDetails details() {
        ordersDetailsBtn.click();
        return new DeliveryDetails(getDriver());
    }


    public Map<String, String> getCartDetails() {

        Map<String, String> productsInCartByQuantity = new HashMap<String, String>();
        for (ExtendedWebElement productInCart : productsInCartStepOne) {
            String name = productInCart.findExtendedWebElement(By.cssSelector("span.title>a")).getText().trim();
            String quantity = productInCart.findExtendedWebElement(By.tagName("input")).getAttribute("value");

            productsInCartByQuantity.put(name, quantity);

        }
        return productsInCartByQuantity;
    }
}
