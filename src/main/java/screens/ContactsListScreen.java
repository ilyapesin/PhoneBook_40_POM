package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactsListScreen extends BaseScreen{

String phoneNamber;
    public ContactsListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityViewText;
    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement moreOptions;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logoutBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement addContactBtn;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement yesBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emptyTxt']")
    MobileElement emptyTxt;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contactList;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> nameList;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> phoneList;


    public boolean isContactListActivityPresent(){
        return shouldHave(activityViewText,"Contact list",5);
    }
    public AuthenticationScreen logout(){
        moreOptions.click();
        logoutBtn.click();
        return new AuthenticationScreen(driver);
    }

    public AddNewContactScreen openContactForm() {
        waitElement(addContactBtn, 15);
        addContactBtn.click();
        return new AddNewContactScreen(driver);
    }
    public boolean isContactAdded(Contact contact){

        boolean checkName = checkContainsText(nameList, contact.getName() + " " + contact.getLastName());
        boolean checkPhone = checkContainsText(phoneList, contact.getPhone());

        return checkName && checkPhone;
    }

    public boolean checkContainsText(List<MobileElement> list, String text){
        for (MobileElement element : list){
            if (element.getText().contains(text)) return true;
        }
        return false;
    }
    public ContactsListScreen removeOneContact(){
        waitElement(addContactBtn,5);
        MobileElement contact = contactList.get(0);
        phoneNamber=phoneList.get(0).getText();
        Rectangle rect = contact.getRect();
        int xStart = rect.getX()+rect.getWidth()/8;
        int xEnd = rect.getX()+rect.getWidth()*6/8;
        int y = rect.getY()+rect.getHeight()/2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xStart,y))
                .moveTo(PointOption.point(xEnd,y))
                .release()
                .perform();
        //yesBtn.click();
        Alert alert = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        alert.accept();
        return this;



    }
    public boolean isContactRemove(){
        return !(phoneList.contains(phoneNamber));
    }
    public ContactsListScreen removeAllContacts(){
        waitElement(addContactBtn,5);
        while (contactList.size() > 0){
            removeOneContact();
        }
        return this;
    }
    public boolean isNoContacts(){
        return shouldHave(emptyTxt,"No Contacts. Add One more!",5);
    }

    public ContactsListScreen provideContacts(){
        while (contactList.size() < 3){
            addNewContacts();
        }
        return this;
    }

    private void addNewContacts() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
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
    public EditContactScreen editOneContact(){
        waitElement(addContactBtn, 5);
        MobileElement contact = contactList.get(0);
        Rectangle rect = contact.getRect();

        int xStart = rect.getX() + rect.getWidth() / 8;
        int xEnd = xStart + rect.getWidth() * 6 / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xEnd, y))
                .moveTo(PointOption.point(xStart, y))
                .release()
                .perform();

        return new EditContactScreen(driver);
    }
    public boolean isContactContains(String email){
        contactList.get(0).click();
        Contact contact = new ViewContactScreen(driver)
                .viewContactObject();
        driver.navigate().back();
        return contact.toString().contains(email);

    }
    public ContactsListScreen scrollPageTo(){
        waitElement(addContactBtn, 5);
        MobileElement contact=contactList.get(contactList.size() - 1);
        Rectangle rect = contact.getRect();
        int xStart=rect.getX()+rect.getWidth()/2;
        int yStart=rect.getY()+rect.getHeight()/2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xStart, yStart))
                .moveTo(PointOption.point(xStart, 0))
                .release()
                .perform();


        return this;
    }
    public boolean isContactAddedScroll(Contact contact){
        boolean result;
        do {
            boolean checkName = checkContainsText(nameList, contact.getName() + " " + contact.getLastName());
            boolean checkPhone = checkContainsText(phoneList, contact.getPhone());
            result = checkName && checkPhone;
            scrollPageTo();
        } while(!result);

        return result;
    }

}

