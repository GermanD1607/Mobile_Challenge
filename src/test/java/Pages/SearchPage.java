package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends Base{
    public SearchPage(AndroidDriver driver){
        super(driver);
    }

    private By item = By.id("com.alibaba.aliexpresshd:id/riv_productsummary_img");

    private By btnFilter = By.id("com.alibaba.aliexpresshd:id/search_filter_img");
    private By priceMin = By.id("com.alibaba.aliexpresshd:id/et_price_from");
    private By priceMax = By.id("com.alibaba.aliexpresshd:id/et_price_to");
    private By btnDoneFilter = By.id("com.alibaba.aliexpresshd:id/btn_apply_refine");

    private By picture = By.id("com.alibaba.aliexpresshd:id/search_product_img");
    private By itemName = By.id("com.alibaba.aliexpresshd:id/tv_product_list_tagged_title");

    public ItemPage openItem(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(item,0));
        driver.findElement(item).click();
        return new ItemPage(driver);
    }
    public void filter(String min, String max){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnFilter,0));
        driver.findElement(btnFilter).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnDoneFilter,0));
        driver.findElement(priceMin).sendKeys(min);
        driver.findElement(priceMax).sendKeys(max);
        driver.findElement(btnDoneFilter).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnFilter,0));
    }
    public String getItemName(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(itemName,0));
        return driver.findElement(itemName).getText();
    }
    public Boolean anyProducts(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(picture,0));
        try {
            driver.findElement(picture);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }
}
