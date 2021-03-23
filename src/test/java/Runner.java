import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Runner extends Hooks{

    private Properties props = new Properties();
    public Runner() {
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (IOException var) {
        }
    }
    @Test
    public void logInTest(){
        Home home = new Home(driver);
        Login login = home.goToAccount();
        login.open();
        login.logIn(props.getProperty("userMail"), props.getProperty("userPassword"));
        Assert.assertTrue(login.checkMessage().contains("Account does not exist."));
    }
    @Test
    public void searchTest(){
        Home home = new Home(driver);
        Search search = home.searchProduct(props.getProperty("searchWord"));
        Assert.assertTrue(search.getItemName().toLowerCase().contains(props.getProperty("searchWord")));
    }
    @Test
    public void addToCartTest() {
        Home home = new Home(driver);
        Search search = home.searchProduct(props.getProperty("searchWord"));
        Item item = search.openItem();
        item.addToCart();
        //assert??
    }
    @Test
    public void verifyCartTest() {
        Home home = new Home(driver);
        Cart cart = home.goToCart();
        Assert.assertTrue(cart.getPageTitle().equals("Cart"));
    }
    @Test
    public void wishListTest() {
        Home home = new Home(driver);
        Search search = home.searchProduct(props.getProperty("searchWord"));
        Item item = search.openItem();
        item.addToWishList();
        Assert.assertTrue(item.getNotification().equals("SIGN IN") || item.getNotification().equals("IDENTIFÍCATE"));
    }
    @Test
    public void imageTest() {
        Home home = new Home(driver);
        Search search = home.imageSearch();
        Assert.assertTrue(search.anyProducts());
    }
    @Test
    public void filterTest() {
        Home home = new Home(driver);
        Search search = home.searchProduct(props.getProperty("searchWord"));
        search.filter(props.getProperty("filterMin"),props.getProperty("filterMax"));
        Item item = search.openItem();
        Assert.assertTrue(item.getPrice()>=Integer.parseInt(props.getProperty("filterMin")) && item.getPrice()<=Integer.parseInt(props.getProperty("filterMax")));
    }
    @Test
    public void shareProductTest() {
        Home home = new Home(driver);
        Search search = home.searchProduct(props.getProperty("searchWord"));
        Item item = search.openItem();
        item.share();
        Assert.assertTrue(item.shareOptions().contains("Copy"));
    }
    @Test
    public void changeCountryTest() {
        Home home = new Home(driver);
        Settings settings = home.goToSettings();
        home = settings.changeShipCountry(props.getProperty("shipCountry"));
        settings = home.goToSettings();
        //Assert.assertTrue(settings.getCountry().equals("Colombia"));
    }
    @Test
    public void changeCurrencyTest() {
        Home home = new Home(driver);
        Settings settings = home.goToSettings();
        home = settings.changeCurrency(props.getProperty("wantedCurrency"));
        settings = home.goToSettings();
        Assert.assertTrue(settings.getCurrency().equals(props.getProperty("wantedCurrency")));
    }
    @Test
    public void verifyPriceTest() {
        Home home = new Home(driver);
        Search search = home.searchProduct(props.getProperty("searchWord"));
        Item item = search.openItem();
        Assert.assertTrue(item.getTextPrice().contains("$"));
    }
}
