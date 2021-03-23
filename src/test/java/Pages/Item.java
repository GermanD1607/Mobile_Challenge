package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Item extends BasePage{
    public Item(AndroidDriver driver){
        super(driver);
    }

    private By priceTag = By.id("com.alibaba.aliexpresshd:id/tv_product_price");
    private By nameTag = By.cssSelector("#com.alibaba.aliexpresshd:id/ultron_recycler_view>TextView");

    private By btnShare = By.id("com.alibaba.aliexpresshd:id/menu_wish_list_group_share");
    private By copyTxt = By.id("com.alibaba.aliexpresshd:id/tv_app_name");

    private By btnCart = By.id("com.alibaba.aliexpresshd:id/ll_addToCart_1");
    private By btnColor = By.cssSelector(".android.widget.RadioGroup .android.widget.CompoundButton");
    private By btnSize = By.cssSelector(".android.widget.RadioGroup .android.widget.CompoundButton");
    private By btnContinue = By.id("com.alibaba.aliexpresshd:id/rl_apply_options");

    private By menu = By.id("com.alibaba.aliexpresshd:id/menu_overflow");
    private By btnWishList = By.id("com.alibaba.aliexpresshd:id/icon");
    private By notification = By.id("com.alibaba.aliexpresshd:id/btn_sign_in");

    public String getTitle(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(nameTag,0));
        return driver.findElement(nameTag).getText();
    }
    public Integer getPrice(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(priceTag,0));

        return Integer.parseInt(driver.findElement(priceTag).getText().
                replaceAll("[^.0-9]", "").substring(0,2));
    }
    public String getTextPrice(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(priceTag,0));

        return driver.findElement(priceTag).getText();
    }
    public void addToCart(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnCart,0));
        driver.findElement(btnCart).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnContinue,0));
        driver.findElement(btnColor).click();
        List<WebElement> options = driver.findElements(btnSize);
        options.get(2).click();
        driver.findElement(btnContinue).click();
    }
    public void share(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnShare,0));
        driver.findElement(btnShare).click();
    }
    public String shareOptions(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(copyTxt,0));
        return driver.findElement(copyTxt).getText();
    }
    public void addToWishList(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(menu,0));
        driver.findElement(menu).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnWishList,0));
        List<WebElement> menuButtons = driver.findElements(btnWishList);
        menuButtons.get(2).click();
    }
    public String getNotification(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(notification,0));
        return driver.findElement(notification).getText();
    }
}
