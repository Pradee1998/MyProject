package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import purchase.formFill;

import java.time.Duration;

public class eCommerce extends Base{



    @Test
    public void formFill1() throws InterruptedException {
        formFill ff= new formFill(driver);
        Thread.sleep(4000);
       ff.sendingName();
         ff.clickMale();
         ff.clickOnRegisterButton();
        Thread.sleep(2000);
//       driver.findElement(By.xpath("//android.widget.RadioButton[@text='Male']")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("android:id/text1")).click();
         driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
//        String toastMessage = driver.findElement(By.xpath("(android.widget.Toast)[1]")).getAttribute("name");
//        Assert.assertEquals(toastMessage,"Please enter your name");


        //Add to cart
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));
  int itemSize= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
  for(int i=0; i<itemSize; i++)
  {
      String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
      System.out.println(productName);
      if(productName.equalsIgnoreCase("Jordan 6 Rings"))
      {
          driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
      }
  }

  driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
Thread.sleep(4000);
WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text","Cart"));
        String cartItem = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(cartItem,"Jordan 6 Rings");

    }
}
