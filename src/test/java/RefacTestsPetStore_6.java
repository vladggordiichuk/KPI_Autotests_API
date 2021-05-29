import com.google.common.collect.ImmutableList;
import io.restassured.response.Response;
import models.Category;
import models.Pet;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class RefacTestsPetStore_6 {

    //public static


    @Test
    public void verifyBody() {
        new PetStorePetEndPoint()
                .getPetByStatus("available")
                .then()
                .assertThat()
                //.log().body()
                .body(Matchers.notNullValue());
    }

    @Test
    public void verifyBodyUseParam() {
        new PetStorePetEndPoint()
                .getPetByStatus("available")
                .then()
                .assertThat()
                //.log().body()
                .body(Matchers.notNullValue());
    }
    @Test
    public void verifyNotExistingPetReturn200() {
        new PetStorePetEndPoint()
                .getPetById("1")
                .then()
                .log().body()
                .statusCode(200);
    }
    @Test
    public void verifyNotExistingPetReturn404() {
        new PetStorePetEndPoint()
                .getPetById("123456789012345")
                .then()
                .log().body()
                .statusCode(404);
    }
    @Test
    public void verifyPetCanBeCreated() {
        Category category = new Category();
        category.setName("Cats");
        category.setId(123123);

        Pet cat = new Pet();
        //cat.setName("Murchyk");
        cat.setName("Murchyk2");
        cat.setCategory(category);
        cat.setPhotoUrls(ImmutableList.of("someUrl"));
        cat.setStatus("available");

        new PetStorePetEndPoint()
                .createPet(cat)
                .then()
                .statusCode(200);

    }
    @Test
    public void verifyPetHasIdAfterCreation() {
        Pet murcyck2 = Pet.createCatPetAvailable(123124, "Murchyk2");

        Response petResponse = new PetStorePetEndPoint()
                .createPet(murcyck2);

        Pet petService = petResponse.body().as(Pet.class);
        Assert.assertNotNull(petService);

    }

}
