package tests.api;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ProductsListApiTest extends BaseApiTest {

    @Test
    public void getAllProductsList(){
        given()
        .when()
            .get("/productsList")
        .then()
            .statusCode(200)
            .body("products.size()", greaterThan(0));
    }

    @Test
    public void postToAllProductList() {
        given()
                .when()
                .post("/productsList")
                .then()
                .statusCode(200)
                .body("responseCode", is(405));
    }

}
