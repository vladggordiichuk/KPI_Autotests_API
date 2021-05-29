import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RefacTestsPetStore {

    /*@Test
    public void verifyStatusCode() {
        given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL)
                .queryParam("status", "available")
                .when()
                .get(Config.PET_BY_STATUS)
                .then()
                .statusCode(200);
    }*/

    @Test
    public void verifyBody() {
        given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL)
                .queryParam("status", "available")
                .when()
                .get(Config.PET_BY_STATUS)
                .then()
                .assertThat()
                //.log().body()
                .body(Matchers.notNullValue());
    }

    @Test
    public void verifyBodyUseParam() {
        given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL)
                .queryParam("status", "available")
                .when()
                .get(Config.PET_BY_STATUS)
                .then()
                .assertThat()
                //.log().body()
                .body(Matchers.notNullValue());
    }
    @Test
    public void verifyNotExistingPetReturn200() {
        given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL)
                .pathParam("petId", "1")
                .when()
                .get(Config.PET_BY_ID)
                .then()
                .log().body()
                .statusCode(200);
    }
    @Test
    public void verifyNotExistingPetReturn404() {
        given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL)
                .pathParam("petId", "123456789012345")
                .when()
                .get(Config.PET_BY_ID)
                .then()
                .log().body()
                .statusCode(404);
    }
}

