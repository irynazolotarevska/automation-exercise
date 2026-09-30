package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailPage;
import pages.ProductsPage;

public class ProductDetailTest extends BaseTest {
    HomePage homePage;
    ProductsPage productsPage;
    ProductDetailPage productDetailPage;


    @BeforeMethod(alwaysRun = true)
    public void setupPage(){
        homePage = new HomePage(page);
        productsPage = new ProductsPage(page);
        productDetailPage = new ProductDetailPage(page);
    }

    @Test(groups = {"smoke"})
    public void verifyAllProductsAndProductDetailPage(){
        homePage.openHomePage();
        Assert.assertTrue(homePage.isLogoVisible(), "Logo should be visible!");
        homePage.clickProductButton();
        Assert.assertTrue(productsPage.isAllProductsHeaderVisible(), "All Products header should be visible.");
        Assert.assertTrue(productsPage.isProductsVisible(), "Products should be visible!");
        productsPage.firstViewProductClicked();
        Assert.assertTrue(productDetailPage.isProductPageWasOpened());
        Assert.assertTrue(productDetailPage.isProductNameVisible());
        Assert.assertTrue(productDetailPage.isCategoryVisible());
        Assert.assertTrue(productDetailPage.isPriceVisible());
        Assert.assertTrue(productDetailPage.isConditionVisible());
        Assert.assertTrue(productDetailPage.isAvailabilityVisible());
        Assert.assertTrue(productDetailPage.isBrandVisible());
    }
}
