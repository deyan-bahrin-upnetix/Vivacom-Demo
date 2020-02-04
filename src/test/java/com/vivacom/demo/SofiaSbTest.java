package com.vivacom.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.vivacom.demo.gui.pages.LoginPageSB.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SofiaSbTest extends AbstractTest {


    @BeforeMethod
    public void maximizeBrowser() {
        getDriver().manage().window().maximize();
    }


    @Test(description = "LoginIntoSystem")
    @MethodOwner(owner = "Deyan Bahrin")
    public void SofiaBTest() throws Exception {

        LoginPageSB loginPageSB = new LoginPageSB(getDriver());
        loginPageSB.open();


        /*Login and mainPage is returned.*/
        MainPageSB mainPageSB = loginPageSB.mainPage();
        Assert.assertTrue(mainPageSB.isProfileSelected(), "Element is not present");

        /*First we open banitsa page thеn we open banitsaOnBites.*/
        Banitsa banitsa = mainPageSB.OpenBanitsaPage();
        BanitsaOnBites banitsaOnBites = banitsa.OpenBnitsaOnBitesPage();

        List<String> productsToOrder = new ArrayList<>();
        productsToOrder.add("Хапки със сирене Пайо 90 - 1.150кг");
        productsToOrder.add("Хапки със бекон и кашкавал Пайо 90 - 1.150кг");
        banitsaOnBites.addProductsToCart(productsToOrder);
        OrdersPage ordersPage = banitsaOnBites.clickShoppingCartButton();

        Map<String,String> productsInCartByQuantity = ordersPage.getCartDetails();
        for (String product : productsToOrder){
            boolean productExistInCart = productsInCartByQuantity.containsKey(product);
            String quantity = productsInCartByQuantity.get(product);
            Assert.assertTrue(productExistInCart, "The product does not exist in the shopping cart");
            Assert.assertEquals(quantity, "1", "The quantity is not one");
        }


        /*fill all details about delivery and assert that invoiceIsOk*/
        DeliveryDetails deliveryDetails = ordersPage.details();
        deliveryDetails.getAdress();
        deliveryDetails.getDateTimeForShiping();
        Assert.assertTrue(deliveryDetails.isInvoiceOk(), "Invoice is not ok");

        /*Take a snapShot and go back to OrderPage.*/
        FinishOrder finishOrder = deliveryDetails.finishTheOrder();
        Assert.assertTrue(finishOrder.isOrderOk(), "Order is not ok");
        finishOrder.takeSnapShot(getDriver());
        ordersPage.deleteTheOrder();
        banitsa.exitFromSystem();


    }

    @AfterMethod
    public void closeBrowser() {
        getDriver().close();
    }

}
