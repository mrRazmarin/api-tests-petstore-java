package com.example.restframework.utils.extension;

import com.example.restframework.api.model.Category;
import com.example.restframework.api.model.PetRoot;
import com.example.restframework.api.model.TagsItem;
import com.example.restframework.utils.CheckDockerContainer;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TestSuiteExtension implements SuiteExtension, ParameterResolver {

    private final CheckDockerContainer checkDockerContainer = new CheckDockerContainer();
    private final GlobalConfig globalConfig = new GlobalConfig();

    private static final String PET_ID_KEY = "sharedPetId";

    @Override
    @Step("Глобальная инициализация: Создание тестового питомца для набора и проверка работы докер-контейнера")
    public void beforeSuite(ExtensionContext extensionContext) {
        globalConfig.globalSettingsRestAssured();

        checkDockerContainer.checkDockerContainer();

        List<TagsItem> listTags = new ArrayList<>();
        listTags.add(TagsItem.builder()
                .name("cat")
                .build()
        );

        Category category = Category.builder()
                .name("Cats")
                .build();

        PetRoot petRequest = PetRoot.builder()
                .tags(listTags)
                .category(category)
                .name("Missa")
                .build();

        Response responseCreatePet = given()
                .contentType(ContentType.JSON.withCharset(StandardCharsets.UTF_8))
                .body(petRequest)
                .post("/pet")
                .then()
                .extract()
                .response();

        Long petId = (long) responseCreatePet.as(PetRoot.class).getId();

        // ВАЖНО: Сохраняем в КОРНЕВОЕ (ROOT) хранилище, чтобы оно было доступно всем тестам
        extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).put(PET_ID_KEY, petId);
    }

    @Override
    @Step("Удаление тестового питомца")
    public void afterSuite(ExtensionContext extensionContext) {
        Long petId = (Long) extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).get(PET_ID_KEY);
         if (petId != null) {
             given().delete("/pet/" + petId);
         }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == Long.class;
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getRoot()
                .getStore(ExtensionContext.Namespace.GLOBAL)
                .get(PET_ID_KEY);
    }
}
