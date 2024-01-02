import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactsListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

@Test
    public void LoginPositive(){
    Assert.assertTrue(new SplashScreen(driver)
            .goToAuthenticationScreen()
            .fillEmail("vasya_pupkin@gmail.com")
            .fillPassword("Vp12345$")
            .submitLogin()
            .isContactListActivityPresent());

}
@AfterMethod
    public void postcondition(){
    if(new ContactsListScreen(driver).isContactListActivityPresent()){
        new ContactsListScreen(driver).logout();
        //new SplashScreen(driver);
    }

}
}
