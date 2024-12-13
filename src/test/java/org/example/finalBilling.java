package org.example;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class finalBilling extends Base {
    @Test
    public void formFill() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Thread.sleep(4000);
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("pradeep");
        driver.hideKeyboard();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Male']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
//        String toastMessage = driver.findElement(By.xpath("(android.widget.Toast)[1]")).getAttribute("name");
//        Assert.assertEquals(toastMessage,"Please enter your name");


        //Add to cart
        Thread.sleep(4000);
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        Thread.sleep(4000);
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(4000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
//        String cartItem = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
//        Assert.assertEquals(cartItem,"Jordan 6 Rings");
        int itemPrizes = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
        double sum = 0;
        for (int i = 0; i < itemPrizes; i++) {
            String textInDallers = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
            double price = Double.parseDouble(textInDallers.substring(1));
            sum = sum + price;
            System.out.println(sum);
        }
        String totalPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double finalPrice = Double.parseDouble(totalPrice.substring(1));
        Assert.assertEquals(sum, finalPrice);
        WebElement visitWeb = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(visitWeb);
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(3000);
        driver.findElement(By.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();


    }
}
