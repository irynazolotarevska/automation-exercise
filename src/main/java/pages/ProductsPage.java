package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductsPage {

    private Page page;

    private Locator allProductsHeader;


    public ProductsPage(Page page) {
        this.page = page;
        allProductsHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("All Products"));
    }

    public boolean isAllProductsHeaderVisible() {
        allProductsHeader.waitFor();
        return allProductsHeader.isVisible();
    }

   /* public  boolean isProductsVisible() {


    }*/
}
