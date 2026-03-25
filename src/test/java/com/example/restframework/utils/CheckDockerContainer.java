package com.example.restframework.utils;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.platform.commons.PreconditionViolationException;

import static io.restassured.RestAssured.given;

public class CheckDockerContainer {

    // Максимальное время ожидания (30 секунд)
    private static final long TIMEOUT_MS = 30000;
    // Интервал проверки (1 секунда)
    private static final long POLL_INTERVAL_MS = 1000;

    private static final Logger logger = LogManager.getLogger(CheckDockerContainer.class);


    public void checkDockerContainer() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < TIMEOUT_MS) {
            try {
                Response response = given().get("/store/inventory")
                        .then()
                        .extract()
                        .response();

                int statusCode = response.statusCode();

                if (statusCode != 200){
                    throw new PreconditionViolationException(
                            String.format("Сервис недоступен! Тесты не будут запущены. Ожидался статус код 200, получен: %d. Тело ответа: %s",
                                    statusCode,
                                    response.getBody().asString()
                            )
                    );
                }else {
                    System.out.println("Сервис успешно поднялся! Статус: " + statusCode);
                    return;
                }

            } catch (Exception e) {
                logger.info("Сервис пока недоступен ({}). Ждем...", e.getClass().getSimpleName());
            }

            try {
                Thread.sleep(POLL_INTERVAL_MS);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new PreconditionViolationException("Проверка прервана");
            }
        }

        throw new PreconditionViolationException(
                String.format("Сервис НЕ поднялся за %d мс. Тесты остановлены.", TIMEOUT_MS)
        );
    }
}
