package Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Base{
    public HomePage(AndroidDriver driver){
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchBar,0));
    }

    private By btnAccount = By.id("com.alibaba.aliexpresshd:id/navigation_my_ae");
    private By btnLogIn = By.id("com.alibaba.aliexpresshd:id/tv_un_login");
    private By btnCart = By.id("com.alibaba.aliexpresshd:id/navigation_cart");

    private By searchBar = By.id("com.alibaba.aliexpresshd:id/rl_search_box");
    private By searchText = By.id("com.alibaba.aliexpresshd:id/abs__search_src_text");
    private By btnSearch = By.id("com.alibaba.aliexpresshd:id/abs__search_go_btn");

    private By btnImageSearch = By.id("com.alibaba.aliexpresshd:id/lay_image_search");
    private By btnAllow = By.id("com.android.packageinstaller:id/permission_allow_button");
    private By btnPhoto = By.id("com.alibaba.aliexpresshd:id/ll_take_photo");

    private By btnSettings = By.id("com.alibaba.aliexpresshd:id/tv_settings");

    public CartPage goToCart() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnCart,0));
        driver.findElement(btnCart).click();
        return new CartPage(driver);
    }
    public LoginPage goToAccount() {
        driver.findElement(btnAccount).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnLogIn,0));
        driver.findElement(btnLogIn).click();
        return new LoginPage(driver);
    }
    public SettingsPage goToSettings() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnAccount, 0));
        driver.findElement(btnAccount).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnSettings, 0));
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int x = driver.manage().window().getSize().width/2;
        int y = driver.manage().window().getSize().height/2;
        action.press(PointOption.point(x, y)).moveTo(PointOption.point(x, y + 50)).release().perform();
        driver.findElement(btnSettings).click();
        return new SettingsPage(driver);
    }
    public SearchPage searchProduct(String searchItem){
        WebElement bar = driver.findElement(searchBar);
        bar.click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchText,0));
        WebElement text = driver.findElement(searchText);
        text.sendKeys(searchItem);
        WebElement button = driver.findElement(btnSearch);
        button.click();
        return new SearchPage(driver);
    }
    public SearchPage imageSearch(){
        driver.findElement(searchBar).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnImageSearch,0));
        driver.findElement(btnImageSearch).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnAllow,0));
        driver.findElement(btnAllow).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnPhoto,0));
        driver.findElement(btnPhoto).click();
        driver.findElement(btnPhoto).click();
        driver.findElement(btnPhoto).click();
        return new SearchPage(driver);
    }
}
