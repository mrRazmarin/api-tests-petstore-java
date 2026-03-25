package com.example.restframework.api.pet;

import com.example.restframework.BaseTest;
import com.example.restframework.steps.PetSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Набор тестов для /pet")
public class PetTests extends BaseTest {

    private final PetSteps petSteps = new PetSteps();

    @Test
    @DisplayName("Получение ранее созданного питомца")
    public void testGetPetById(Long petId) {
        System.out.println("Получен ID из фикстуры: " + petId);

        Integer expectedStatusCode = 200;

        Response response = petSteps.sendRequestForGetInfo("/pet", petId);

        petSteps.checkStatusCode(response, expectedStatusCode);
    }
}
