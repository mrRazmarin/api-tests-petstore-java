package com.example.restframework.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
/**
    Шаги выполнения для эндпоинта '/pet'
 */
public class PetSteps {
    @Step("Получить ответ по запросу информации об питомце")
    public Response sendRequestForGetInfo(String path, Long petId) {
        return given()
                .pathParam("id", petId)
                .when()
                .get(path + "/{id}");
    }

    @Step("Проверить статус код ответа")
    public void checkStatusCode(Response response, Integer expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }
}
