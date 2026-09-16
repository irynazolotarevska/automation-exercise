package tests.api;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class SearchProductApiTest extends BaseApiTest {

    @Test
    public void postToSearchProductWithParam(){
        given()
                .formParam("search_product", "top")
        .when()
                .post("/searchProduct")
        .then()
                .body("responseCode", is(200))
                .body("products.size()", greaterThan(1))
                .body("products[0].category.category", is("Tops"));
    }

    @Test
    public void postToSearchProductWithoutParam(){
        given()
        .when()
                .post("/searchProduct")
        .then()
                .body("responseCode", is(400));
    }

}
