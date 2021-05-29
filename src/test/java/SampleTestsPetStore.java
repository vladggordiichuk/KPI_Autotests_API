import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class SampleTestsPetStore {

    @Test
    public void verifyStatusCode() {
        given()
                //.log().uri()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get("pet/findByStatus?status=available")
                .then()
                .statusCode(200);
    }
    @Test
    public void verifyStatusCodeAndLog() {
        given()
                .log().uri()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get("/pet/findByStatus?status=available")
                .then()
                .statusCode(200);
    }

    @Test
    public void verifyBody() {
        given()
                .log().uri()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get("/pet/findByStatus?status=available")
                .then()
                .assertThat()
               // .log().body()
                .body(Matchers.notNullValue());
    }

    @Test
    public void verifyBodyUseParam() {
        given()
                .log().uri()
                .baseUri("https://petstore.swagger.io/v2")
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus")
                .then()
                .assertThat()
                //.log().body()
                .body(Matchers.notNullValue());
    }
    @Test
    public void verifyNotExistingPetReturn200() {
        given()
                .log().uri()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get("/pet/1")
                .then()
                .log().body()
                .statusCode(200);
    }
    @Test
    public void verifyNotExistingPetReturn404() {
        given()
                .log().uri()
                .baseUri("https://petstore.swagger.io/v2")
                .pathParam("petId", "123456789012345")
                .when()
                .get("/pet/{petId}")
                .then()
                .log().body()
                .statusCode(404);
    }
}
