package com.vivacom.demo;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.vivacom.demo.gui.pages.LoginPageSB.*;
import com.vivacom.demo.gui.pages.LoginPageSB.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
        Assert.assertTrue(mainPageSB.selectProfile(), "Element is not present");

        /*First we open banitsa page thеn we open banitsaOnBites.*/
        Banitsa banitsa = mainPageSB.OpenBanitsaPage();
        BanitsaOnBites banitsaOnBites = banitsa.OpenBnitsaOnBitesPage();

        /*Opens the OrdersPage and validate that the products are the same which we order.*/
        OrdersPage ordersPage = banitsaOnBites.openOrdersPage();
        Assert.assertTrue(ordersPage.compareTheOrder(), "This is not the products");

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
