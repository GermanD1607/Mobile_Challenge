package Pages;

import io.appium.java_client.android.AndroidDriver;

public class Base {
    protected AndroidDriver driver;
    public Base(AndroidDriver driver){
        this.driver = driver;
    }
}
