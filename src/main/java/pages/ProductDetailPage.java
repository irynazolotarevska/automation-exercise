package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductDetailPage {
    private Page page;
    public static final String PRODUCT_DETAILS_PAGE = "https://automationexercise.com/product_details/1";

    private Locator productName;
    private Locator productCategory;
    private Locator productPrice;
    private Locator productAvailability;
    private Locator productCondition;
    private Locator productBrand;

    public ProductDetailPage(Page page) {
        this.page = page;
        productName =  page.locator(".product-information h2");
        productCategory =  page.getByText("Category:");
        productPrice = page.getByText("Rs.");
        productAvailability = page.getByText("Availability:");
        productCondition = page.getByText("Condition:");
        productBrand = page.getByText("Brand:");
    }

    public boolean isProductPageWasOpened() {
       return page.url().contains(PRODUCT_DETAILS_PAGE);
    }

    public boolean isProductNameVisible() {
        productName.waitFor();
        return productName.isVisible();
    }

    public boolean isCategoryVisible(){
        productCategory.waitFor();
        return productCategory.isVisible();
    }

    public boolean isPriceVisible(){
        productPrice.waitFor();
        return productPrice.isVisible();
    }

    public boolean isAvailabilityVisible(){
        productAvailability.waitFor();
        return productAvailability.isVisible();
    }

    public boolean isConditionVisible(){
        productCondition.waitFor();
        return productCondition.isVisible();
    }

    public boolean isBrandVisible(){
        productBrand.waitFor();
        return productBrand.isVisible();
    }





    }

