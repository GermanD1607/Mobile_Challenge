package Pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SettingsPage extends Base{
    public SettingsPage(AndroidDriver driver){
        super(driver);
    }
    private By btnCurrency = By.id("com.alibaba.aliexpresshd:id/rl_currency_settings");
    private By currencySearch = By.id("com.alibaba.aliexpresshd:id/tv_auto_complete_query");
    private By selectCurrency = By.id("com.alibaba.aliexpresshd:id/rl_select_currency_item");
    private By btnGotIt = By.id("com.alibaba.aliexpresshd:id/snackbar_action");

    private By currencyTxt = By.id("com.alibaba.aliexpresshd:id/tv_settings_currency_view");
    private By countryTxt = By.id("com.alibaba.aliexpresshd:id/iv_country_selected");

    private By btnShipTo = By.id("com.alibaba.aliexpresshd:id/rl_country_settings");
    private By countryOption = By.id("com.alibaba.aliexpresshd:id/tv_country_value");

    public HomePage changeCurrency(String currencyName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnCurrency,0));
        WebElement button = driver.findElement(btnCurrency);
        button.click();

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(currencySearch,0));
        driver.findElement(currencySearch).sendKeys(currencyName);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(selectCurrency,0));
        driver.findElement(selectCurrency).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnGotIt,0));
        driver.findElement(btnGotIt).click();

        return new HomePage(driver);
    }
    public String getCurrency(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(currencyTxt,0));
        System.out.println(driver.findElement(currencyTxt).getText());
        return driver.findElement(currencyTxt).getText();
    }
    public String getCountry(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(countryTxt,0));
        return driver.findElement(countryTxt).getText();
    }
    public HomePage changeShipCountry(String countryName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(btnShipTo,0));
        driver.findElement(btnShipTo).click();
        WebElement selectedCountry = null;
        List<WebElement> countries = driver.findElements(countryOption);
        for(int i = 0; i<countries.size();i++){
            if(countries.get(i).getText().equals(countryName)){
                selectedCountry = countries.get(i);
            }
        }
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int x = selectedCountry.getLocation().getX();
        int y = selectedCountry.getLocation().getY();
        action.press(PointOption.point(x,y)).moveTo(PointOption.point(x,y+50)).release().perform();
        selectedCountry.click();
        return new HomePage(driver);
    }
}
