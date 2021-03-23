import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Runner extends NewHooks{

    private Properties props = new Properties();
    public Runner() {
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (IOException var) {
        }
    }
    @Test
    public void logInTest(){
        HomePage home = new HomePage(driver);
        LoginPage login = home.goToAccount();
        login.open();
        login.logIn(props.getProperty("userMail"), props.getProperty("userPassword"));
        Assert.assertTrue(login.checkMessage().contains("Account does not exist."));
    }
    @Test
    public void searchTest(){
        HomePage home = new HomePage(driver);
        SearchPage search = home.searchProduct(props.getProperty("searchWord"));
        Assert.assertTrue(search.getItemName().toLowerCase().contains(props.getProperty("searchWord")));
    }
    @Test
    public void addToCartTest() {
        HomePage home = new HomePage(driver);
        SearchPage search = home.searchProduct(props.getProperty("searchWord"));
        ItemPage item = search.openItem();
        item.addToCart();
    }
    @Test
    public void verifyCartTest() {
        HomePage home = new HomePage(driver);
        CartPage cart = home.goToCart();
        Assert.assertTrue(cart.getPageTitle().equals("Cart"));
    }
    @Test
    public void wishListTest() {
        HomePage home = new HomePage(driver);
        SearchPage search = home.searchProduct(props.getProperty("searchWord"));
        ItemPage item = search.openItem();
        item.addToWishList();
        Assert.assertTrue(item.getNotification().equals("SIGN IN") || item.getNotification().equals("IDENTIFÍCATE"));
    }
    @Test
    public void imageTest() {
        HomePage home = new HomePage(driver);
        SearchPage search = home.imageSearch();
        Assert.assertTrue(search.anyProducts());
    }
    @Test
    public void filterTest() {
        HomePage home = new HomePage(driver);
        SearchPage search = home.searchProduct(props.getProperty("searchWord"));
        search.filter(props.getProperty("filterMin"),props.getProperty("filterMax"));
        ItemPage item = search.openItem();
        Assert.assertTrue(item.getPrice()>=Integer.parseInt(props.getProperty("filterMin")) && item.getPrice()<=Integer.parseInt(props.getProperty("filterMax")));
    }
    @Test
    public void shareProductTest() {
        HomePage home = new HomePage(driver);
        SearchPage search = home.searchProduct(props.getProperty("searchWord"));
        ItemPage item = search.openItem();
        item.share();
        Assert.assertTrue(item.shareOptions().contains("Copy"));
    }
    @Test
    public void changeCountryTest() {
        HomePage home = new HomePage(driver);
        SettingsPage settings = home.goToSettings();
        home = settings.changeShipCountry(props.getProperty("shipCountry"));
        settings = home.goToSettings();
        //Assert.assertTrue(settings.getCountry().equals("Colombia"));
    }
    @Test
    public void changeCurrencyTest() {
        HomePage home = new HomePage(driver);
        SettingsPage settings = home.goToSettings();
        home = settings.changeCurrency(props.getProperty("wantedCurrency"));
        settings = home.goToSettings();
        Assert.assertTrue(settings.getCurrency().equals(props.getProperty("wantedCurrency")));
    }
    @Test
    public void verifyPriceTest() {
        HomePage home = new HomePage(driver);
        SearchPage search = home.searchProduct(props.getProperty("searchWord"));
        ItemPage item = search.openItem();
        Assert.assertTrue(item.getTextPrice().contains("$"));
    }
}
