package tests.api;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class LoginApiTest extends BaseApiTest {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][] {
                { ConfigReader.getExistingUserEmail(), "123456" }
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                { "testforzoloto78@gmail.com", "123456" },
                { ConfigReader.getExistingUserEmail(), "1234567" }
        };
    }

    @Test(dataProvider = "validLoginData")
    public void postValidLoginApiTest(String email, String password){
        given()
                .formParam("email", email)
                .formParam("password", password)
        .when()
                .post("/verifyLogin")
        .then()
                .body("responseCode", is(200));
    }

    @Test(dataProvider = "invalidLoginData")
    public void postInvalidLoginApiTest(String email, String password){
        given()
                .formParam("email", email)
                .formParam("password", password)
        .when()
                .post("/verifyLogin")
        .then()
                .body("responseCode", is(404));
    }

}
