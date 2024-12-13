package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class test3 extends Base {
@Test

    public void longPress() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement peopleName = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
//        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
//                ImmutableMap.of("elementId", ((RemoteWebElement) peopleName).getId()));
    longPressAction(peopleName);
        Thread.sleep(2000);
    String sampleMenu = driver.findElement(By.id("android:id/title")).getText();
    Assert.assertEquals(sampleMenu, "Sample menu");
    Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());
    }
}
