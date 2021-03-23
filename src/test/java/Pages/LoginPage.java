package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Base{
    public LoginPage(AndroidDriver driver){
        super(driver);
    }

    private By btnSignIn = By.id("com.alibaba.aliexpresshd:id/btn_sign_in");

    private By email = By.id("com.alibaba.aliexpresshd:id/et_email");
    private By password = By.id("com.alibaba.aliexpresshd:id/et_password");
    private By enterButton = By.id("com.alibaba.aliexpresshd:id/rl_ali_sign_in_btn");

    private By message = By.id("android:id/message");

    public void open() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnSignIn,0));
        WebElement signIn = driver.findElement(btnSignIn);
        signIn.click();
    }
    public void logIn(String userMail, String userPass) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(enterButton,0));
        WebElement mail = driver.findElement(email);
        mail.sendKeys(userMail);
        WebElement pass = driver.findElement(password);
        pass.sendKeys(userPass);
        WebElement enter = driver.findElement(enterButton);
        enter.click();
    }
    public String checkMessage() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(message,0));
        WebElement popUp = driver.findElement(message);
        return popUp.getText();
    }
}
