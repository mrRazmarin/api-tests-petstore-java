package com.example.restframework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    private static String getValueProperty(String propertyName, String defaultValue) {
        String envValue = System.getenv(propertyName);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }

        Properties properties = new Properties();

        try (InputStream inputStream = ReadProperties
                .class
                .getClassLoader()
                .getResourceAsStream("application.properties")
        ) {
            if (inputStream != null) {
                properties.load(inputStream);
                return properties.getProperty(propertyName);
            }else
                return defaultValue;

        } catch (IOException e) {
            System.err.println("Не удалось прочитать файл конфигурации: " + e.getMessage());
            return defaultValue;
        }
    }

    public final static String PETSTORE_URL = getValueProperty("PETSTORE_URL", "http://localhost");
    public final static String PETSTORE_PORT = getValueProperty("PETSTORE_PORT", "8080");
    public final static String PETSTORE_PATH = getValueProperty("PETSTORE_PATH", "/v2");
}
