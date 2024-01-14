import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactsListScreen;
import screens.SplashScreen;

public class RemoveContactsTest extends AppiumConfig {

    @BeforeMethod
    public void precondition() {
        new SplashScreen(driver)
                .goToAuthenticationScreen()
                .fillEmail("vasya_pupkin1737@gmail.com")
                .fillPassword("Vp12345$")
                .submitLogin();
        new ContactsListScreen(driver).provideContacts();

    }
    @Test
    public void removeOneContactPositive(){
        Assert.assertTrue(
        new ContactsListScreen(driver)
                .removeOneContact()
                .isContactRemove()
        );
    }
    @Test
    public void removeAllContactsPositive(){
        Assert.assertTrue(
        new ContactsListScreen(driver)
                .removeAllContacts()
                .isNoContacts()
        );

    }

}
