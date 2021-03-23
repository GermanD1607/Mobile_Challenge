package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends Base{
    public CartPage(AndroidDriver driver){
        super(driver);
    }

    private By pageTitle = By.id("com.alibaba.aliexpresshd:id/tv_shop_cart_title");
    private By itemTitle = By.id("com.alibaba.aliexpresshd:id/tv_product_title");

    public String getPageTitle(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pageTitle,0));
        return driver.findElement(pageTitle).getText();
    }
    public Boolean anyProducts(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pageTitle,0));
        try {
            driver.findElement(itemTitle);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }
}
