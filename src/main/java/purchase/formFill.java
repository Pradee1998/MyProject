package purchase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class formFill {
    AndroidDriver driver;

    // Constructor for initializing the PageFactory with AppiumFieldDecorator
    public formFill(AndroidDriver driver) {
        this.driver = driver;
        System.out.println("Driver before PageFactory: " + driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
      //  PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Locating elements using @AndroidFindBy
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    private WebElement male;

    @AndroidFindBy(id = "android:id/text1")
    private WebElement register;

    // Method for entering name and hiding the keyboard
    public void sendingName() {
        nameField.sendKeys("pradeep");
        driver.hideKeyboard();
    }

    // Method to click on the 'Male' radio button
    public void clickMale() {
        male.click();
    }

    // Method to click on the register button
    public void clickOnRegisterButton() {
        register.click();
    }
}