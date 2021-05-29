import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RefacTestsPetStore_2 {

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
                .spec(createBaseSpec())
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
                .spec(createBaseSpec())
                .pathParam("petId", "123456789012345")
                .when()
                .get(Config.PET_BY_ID)
                .then()
                .log().body()
                .statusCode(404);
    }

    private RequestSpecification createBaseSpec() {
        return given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL);
    }
}

