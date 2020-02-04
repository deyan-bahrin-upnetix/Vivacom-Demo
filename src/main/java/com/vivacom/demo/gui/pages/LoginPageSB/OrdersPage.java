package com.vivacom.demo.gui.pages.LoginPageSB;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

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



    public void deleteTheOrder(){
        orderIsEmptyBtn.click(10);

    }

    public DeliveryDetails details(){
        ordersDetailsBtn.click();
        return new DeliveryDetails(getDriver());
    }

    public boolean compareTheOrder(){
        String text = selectFirstProduct.getText();
        String text2 = selectSecondProduct.getText();
        String quantityFirstProductAttribute = quantityFirstProduct.getAttribute("value");
        String quantitySecondProductAttribute = quantitySecondProduct.getAttribute("value");
        WebDriverWait wait = new WebDriverWait(getDriver(), 3);
        if(text.equals("Хапки със сирене Пайо 90 - 1.150кг") & (text2.equals("Хапки със бекон и кашкавал Пайо 90 - 1.150кг"))){
            return true;
        }

        else if(quantityFirstProductAttribute.equals("1") & quantitySecondProductAttribute.equals("1")){
            return true;
        }
        return false;

    }






}
