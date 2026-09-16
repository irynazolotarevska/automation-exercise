package tests.api;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;

public class BaseApiTest {

    @BeforeClass
    public void before() {
        baseURI = "https://automationexercise.com/api";
        RestAssured.registerParser("text/html", Parser.JSON);
    }
}
