package se.nackademin;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class AppIT {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void validates_hello_message() {
        when().get().then()
                .statusCode(200)
                .body("html.head.title", equalTo("Welcome to nginx!"));
    }
}