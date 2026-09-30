package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductsPage {

    private Page page;

    private Locator allProductsHeader;
    private Locator productItems;
    private Locator viewProduct;


    public ProductsPage(Page page) {
        this.page = page;
        allProductsHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("All Products"));
        productItems = page.locator(".product-image-wrapper");
        viewProduct = page.locator(".choose > .nav > li > a");
    }

    public boolean isAllProductsHeaderVisible() {
        allProductsHeader.waitFor();
        return allProductsHeader.isVisible();
    }

    public boolean isProductsVisible() {
        productItems.first().waitFor();
        return productItems.first().isVisible();
    }

    public void firstViewProductClicked(){
        viewProduct.first().click();
    }
}
