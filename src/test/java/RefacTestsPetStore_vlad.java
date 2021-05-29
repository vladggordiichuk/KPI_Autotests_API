import com.google.common.collect.ImmutableList;
import io.restassured.response.Response;
import models.Category;
import models.Pet;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class RefacTestsPetStore_vlad {

    //public static


    @Test
    public void updatePet() {

        String newPetName = "Doge [new name]";
        String newPetStatus = "sold";

        Response petUpdateResponse = new PetStorePetEndPoint()
            .updatePet("123321", newPetName, newPetStatus);

        petUpdateResponse
            .then()
            .assertThat()
            .statusCode(200);

        Response petGetResponse = new PetStorePetEndPoint()
            .getPetById("123321");

        petGetResponse
            .then()
            .assertThat()
            .statusCode(200);
        
        Pet updatedPet = petGetResponse.body().as(Pet.class);

        Assert.assertNotNull(updatedPet);
        Assert.assertEquals(updatedPet.getName(), newPetName);
        Assert.assertEquals(updatedPet.getStatus(), newPetStatus);
    }
}
