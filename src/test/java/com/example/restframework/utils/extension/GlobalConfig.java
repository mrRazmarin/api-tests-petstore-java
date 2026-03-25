package com.example.restframework.utils.extension;

import com.example.restframework.utils.ReadProperties;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import java.nio.charset.StandardCharsets;

public class GlobalConfig {
    public void globalSettingsRestAssured() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(ReadProperties.PETSTORE_URL)
                .setBasePath(ReadProperties.PETSTORE_PATH)
                .setPort(Integer.parseInt(ReadProperties.PETSTORE_PORT))
                .setContentType(ContentType.JSON.withCharset(StandardCharsets.UTF_8));

        RestAssured.requestSpecification = requestSpecBuilder.build();

        RestAssured.filters(new AllureRestAssured(), new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
