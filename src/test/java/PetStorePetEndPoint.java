import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Pet;
import java.util.HashMap;
import java.io.*;

public class PetStorePetEndPoint {

    public Response getPetById(String id) {
        return given()
                .pathParam("petId", id)
                .when()
                .get(Config.PET_BY_ID)
                .then().extract().response();
    }

    public Response getPetByStatus(String status) {
        return given()
                .queryParam("status", status)
                .when()
                .get(Config.PET_BY_STATUS)
                .then().extract().response();
    }

    public Response createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(Config.CREATE_PET)
                .then().extract().response();
    }

    public Response updatePet(String id, String name, String status) {

        Map<String, String> formParams  = new HashMap<String, String>();
        formParams.put("name", name);
        formParams.put("status", status);

        return given()
                .pathParam("petId", id)
                .formParams(formParams)
                .contentType(ContentType.URLENC)
                .accept(ContentType.JSON)
                // .formParam("name", name)
                // .formParam("status", status)
                .when()
                .post(Config.UPDATE_PET)
                .then().extract().response();
    }

    public Response deleteById(long id) {
        return given()
                .when()
                .delete(Config.PET_BY_ID, id)
                .then().extract().response();
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(Config.PETSTORE_BASE_URL)
                .contentType(ContentType.JSON);
    }
}
