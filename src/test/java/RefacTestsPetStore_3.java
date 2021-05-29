import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

public class RefacTestsPetStore_3 {

    @Test
    public void verifyBody() {
        given()
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
                .pathParam("petId", "123456789012345")
                .when()
                .get(Config.PET_BY_ID)
                .then()
                .log().body()
                .statusCode(404);
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .log().uri()
                .baseUri(Config.PETSTORE_BASE_URL);
    }
}



