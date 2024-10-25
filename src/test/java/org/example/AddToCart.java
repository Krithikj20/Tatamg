package org.example;

import org.testng.annotations.Test;

public class AddToCart extends BaseTest{
    @Test
    public void AddtocartTest(){
    AddToCartPageObjects cart=new AddToCartPageObjects(driver);
    cart.searchFlow();
    }
}
