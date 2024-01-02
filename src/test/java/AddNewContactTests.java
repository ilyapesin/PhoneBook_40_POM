import config.AppiumConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.BaseScreen;
import screens.ContactsListScreen;
import screens.SplashScreen;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {
   // int i = (int) (System.currentTimeMillis() / 1000) % 3600;
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
    public void addNewContactPositive() {
        Contact contact = Contact.builder()
                .name("John_" + i)
                .lastName("Snow")
                .phone("01234578" + i)
                .email("john_" + i + "@mail.com")
                .address("Rehovot")
                .description("Best friend")
                .build();
        new ContactsListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitCreate();
    }

    @AfterMethod
    public void postcondition() {
        if (new ContactsListScreen(driver).isContactListActivityPresent()) {
            new ContactsListScreen(driver).logout();
            //new SplashScreen(driver);
        }
    }
}
