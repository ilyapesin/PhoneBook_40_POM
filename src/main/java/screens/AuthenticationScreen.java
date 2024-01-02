package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationScreen extends BaseScreen{
    public AuthenticationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityViewText;
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement inputEmail;
    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    MobileElement inputPassword;
    @FindBy(id = "com.sheygam.contactapp:id/regBtn")
    MobileElement regBtn;
    @FindBy(id = "com.sheygam.contactapp:id/loginBtn")
    MobileElement loginBtn;

    public AuthenticationScreen fillEmail(String email) {
        waitElement(inputEmail,5);
        type(inputEmail,email);
        return this;
    }
    public AuthenticationScreen fillPassword(String password) {
        waitElement(inputPassword,5);
        type(inputPassword,password);
        return this;
    }

    public ContactsListScreen submitLogin() {
        loginBtn.click();
        return new ContactsListScreen(driver);
    }

    public ContactsListScreen submitRegistration() {
        regBtn.click();
        return new ContactsListScreen(driver);

    }
    public AuthenticationScreen submitRegistrationNegative() {
        regBtn.click();
        return this;

    }
}
