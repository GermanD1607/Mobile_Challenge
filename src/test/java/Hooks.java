import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class Hooks {
    protected AndroidDriver driver;

    private DesiredCapabilities capabilities = new DesiredCapabilities();

    @BeforeMethod
    public void setup() throws MalformedURLException {
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("deviceName", "Emulator");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.alibaba.aliexpresshd");
        capabilities.setCapability("appActivity", "com.alibaba.aliexpresshd.home.ui.MainActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}
