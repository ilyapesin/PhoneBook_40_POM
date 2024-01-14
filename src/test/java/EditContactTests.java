import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactsListScreen;
import screens.SplashScreen;

import java.util.Random;

public class EditContactTests extends AppiumConfig {

   int i = new Random().nextInt(1000) + 1000;

    @BeforeMethod
    public void precondition() {
        new SplashScreen(driver)
                .goToAuthenticationScreen()
                .fillEmail("vasya_pupkin@gmail.com")
                .fillPassword("Vp12345$")
                .submitLogin();

    }
    @Test
    public void editOneContactPositive(){
        String email = "jhon_update"+i+"@gmail.com",address = "Hifa";
        Assert.assertTrue(
        new ContactsListScreen(driver)
                .editOneContact()
                .editContact(email,address)
                .submitEditContact()
                .isContactContains(email)
        );
    }
}
