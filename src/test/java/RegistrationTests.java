import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {
    int i = new Random().nextInt(1000) + 1000;
    @Test
    public void RegistrationPositive(){
        Assert.assertTrue(new SplashScreen(driver)
                .goToAuthenticationScreen()
                .fillEmail("vasya_pupkin"+i+"@gmail.com")
                .fillPassword("Vp12345$")
                .submitRegistration()
                .isContactListActivityPresent());

    }

    @Test
    public void RegistrationNegativeWrongEmail(){
       Assert.assertTrue(
                new SplashScreen(driver)
                .goToAuthenticationScreen()
                .fillEmail("vasya_pupkin"+i+"gmail.com")
                .fillPassword("Vp12345$")
                .submitRegistrationNegative()
             .isContactListActivityPresent());

    }

}
