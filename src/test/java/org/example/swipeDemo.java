package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class swipeDemo extends Base {

    @Test
    public void swipeTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        WebElement Image = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
        Assert.assertEquals(Image.getAttribute("focusable"),"true");
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId",((RemoteWebElement)Image).getId(),
                "direction", "left",
                "percent", 0.75
        ));
        Assert.assertEquals(Image.getAttribute("focusable"),"false");
    }

}
