package pages;

import com.microsoft.playwright.Page;

public class AccountInformationPage {
    private Page page;

    /* Enter Account Infoormation */
    private String headerText1 = "//b[normalize-space()='Enter Account Information']";
    private String titleMrs = "#id_gender2";
    private String passwordField = "#password";
    private String dayOfBirth = "#days";
    private String monthOfBirth = "#months";
    private String yearOfBirth = "#years";
    private String signUpForNewsLetter = "#newsletter";
    private String receiveSpecialOffers = "#optin";

    /* Address Information */
    private String headerText2 = "//b[normalize-space()='Address Information']";
    private String firstNameField = "#first_name";
    private String lastNameField = "#last_name";
    private String companyField = "#company";
    private String addressField = "#address1";
    private String address2Field = "#address2";
    private String countryField = "#country";
    private String stateField = "#state";
    private String cityField = "#city";
    private String zipcodeField = "#zipcode";
    private String mobileNumberField = "#mobile_number";
    private String createAccountButton = "button[data-qa='create-account']";

    public AccountInformationPage(Page page) { this.page = page; }
    /* Enter Account Infoormation */
    public boolean isAccountInformationHeaderVisible() {
        return page.locator(headerText1).isVisible();
    }
    public void selectTitleMrs(){
        page.locator(titleMrs).click();
    }
    public void fillPassword(String password) {
        page.locator(passwordField).fill(password);
    }
    public void selectDateOfBirth(String day,String month, String year){
        page.locator(dayOfBirth).selectOption(day);
        page.locator(monthOfBirth).selectOption(month);
        page.locator(yearOfBirth).selectOption(year);
    }
    public void selectSignUpForNewsLetter(){
        page.locator(signUpForNewsLetter).click();
    }
    public void selectReceiveSpecialOffers(){
        page.locator(receiveSpecialOffers).click();
    }

    /* Address Information */
    public boolean isAddressInformationHeader2Visible(){
        return page.locator(headerText2).isVisible();
    }
    public void fillFirstName(String firstName) {
        page.locator(firstNameField).fill(firstName);
    }
    public void fillLastName(String lastName){
        page.locator(lastNameField).fill(lastName);
    }
    public void fillCompany(String company){
        page.locator(companyField).fill(company);
    }
    public void fillAddress(String address) {
        page.locator(addressField).fill(address);
    }
    public void fillAddress2(String address2){
        page.locator(address2Field).fill(address2);
    }
    public void selectCountry(String country) {
        page.locator(countryField).selectOption(country);
    }
    public void fillState(String state){
        page.locator(stateField).fill(state);
    }
    public void fillCity(String city){
        page.locator(cityField).fill(city);
    }
    public void fillZipcode(String zipcode){
        page.locator(zipcodeField).fill(zipcode);
    }
    public void fillMobileNumber(String mobileNumber){
        page.locator(mobileNumberField).fill(mobileNumber);
    }
    public void clickOnCreateAccount(){
        page.locator(createAccountButton).click();
    }

    public void enterAccountInformation(String password, String day,String month, String year){
        selectTitleMrs();
        fillPassword(password);
        selectDateOfBirth(day, month, year);
        selectSignUpForNewsLetter();
        selectReceiveSpecialOffers();
    }
    public void enterAddressInformation(String firstName,String lastName, String company, String address,
                                        String address2, String country, String state, String city,
                                        String zipcode, String mobileNumber){
        fillFirstName(firstName);
        fillLastName(lastName);
        fillCompany(company);
        fillAddress(address);
        fillAddress2(address2);
        selectCountry(country);
        fillState(state);
        fillCity(city);
        fillZipcode(zipcode);
        fillMobileNumber(mobileNumber);
        clickOnCreateAccount();
    }

}
