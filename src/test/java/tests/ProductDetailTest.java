package tests;

import org.testng.Assert;
import pages.HomePage;
import pages.ProductsPage;

public class ProductDetailTest extends BaseTest {
    HomePage homePage;
    ProductsPage productsPage;

    public void setupPage(){
        homePage = new HomePage(page);
        productsPage = new ProductsPage(page);
    }

    public void verifyAllProductsAndProductDetailPage(){
        homePage.openHomePage();
        Assert.assertTrue(homePage.isLogoVisible(), "Logo should be visible!");
        homePage.clickProductButton();
        productsPage.isAllProductsHeaderVisible();


    }
}
