# 🚀 Java API Automation Framework

![Java](https://img.shields.io/badge/Java-17+-blue.svg)
![Gradle](https://img.shields.io/badge/Gradle-9.0.0-green.svg)
![Junit5](https://img.shields.io/badge/Junit5-Latest-green.svg)
![Allure](https://img.shields.io/badge/Allure-Report-red.svg)

> 🧠 Промышленный фреймворк для автоматизации тестирования API на Java.
> Проект демонстрирует лучшие практики (Best Practices): чистую архитектуру,
> паттерн API Object Model (аналог Page Object для API), использование RestAssured и генерацию детальных отчетов в Allure.

> Цель — построить понимание написания фреймворка для автоматизированного тестирования backend'а с нуля на Java.

---

## 📦 Технологический стек
*   **Java 17+**: Язык программирования.
*   **Gradle 9.0.0**: Система сборки и управления зависимостями.
*   **Junit5**: Фреймворк для запуска тестов.
*   **RestAssured**: Библиотека для выполнения HTTP-запросов и проверки ответов.
*   **Allure Report**: Инструмент для построения красивых и интерактивных отчетов.
*   **Jackson/Gson**: Сериализация и десериализация JSON объектов (входит в состав RestAssured).

---

## 🏗 Архитектура проекта

Проект разделен на логические слои для поддержки масштабируемости и читаемости кода.

```text
api-autotests-petstore/
├── .github/
│   └── workflows/
│       ├── api-tests.yml              <-- Конфигурация CI/CD (GitHub Actions)
│       └── qodana_code_quality.yml    <-- Конфигурация для статического анализа кода
├── src/
│   └── test/
│       ├── java/
│       │   └── com/example/restframework/
│       │       ├── BaseTest.java       <-- Базовый класс для общих настроек (Specs, BaseURI)
│       │       ├── api/                <-- Слой API (интеракция с сервером)
│       │       │   ├── endpoints/      <-- Эндпоинты (пути к ресурсам)
│       │       │   │   └── PetEndpoint.java
│       │       │   ├── models/         <-- POJO классы (Request/Response DTO)
│       │       │   │   └── Pet.java
│       │       │   └── requests/       <-- Билдеры запросов (если сложная логика)
│       │       ├── config/             <-- Конфигурация (Config.java)
│       │       ├── utils/              <-- Утилиты (DataGenerator, JsonHelper)
│       │       └── tests/              <-- Тесты (сценарии)
│       │           ├── smoke/          <-- Смоук тесты
│       │           └── regression/     <-- Регрессионные тесты
│       └── resources/                  <-- Ресурсы
│           ├── allure.properties       <-- Настройки Allure
│           └── application.properties  <-- Конфигурация приложения
├── build.gradle                        <-- Зависимости и скрипты сборки
├── settings.gradle
├── gradlew / gradlew.bat               <-- Gradle Wrapper
├── qodana.yaml                         <-- Конфигурация воркфлоу Qodana
└── .gitignore
└── README.md
```

---

## 🚀 Установка и настройка

### 1. Клонирование репозитория
```bash
git clone <your-repo-url>
cd api-autotests-petstore
```

### 2. Требования к окружению
*   **JDK 17**: Убедитесь, что Java установлена и переменная `JAVA_HOME` настроена.
    *   Проверка: `java -version`
*   **Gradle**: Будет использован Gradle Wrapper (входит в репозиторий), устанавливать Gradle глобально не обязательно.

### 3. Установка зависимостей
Gradle автоматически скачает все зависимости при первой сборке.
```bash
./gradlew build
```

### 4. Установка Allure Commandline
Для просмотра отчетов необходима утилита Allure.

*   **macOS:** `brew install allure`
*   **Windows (Chocolatey):** `choco install allure`
*   **Скачивание:** [GitHub Releases](https://github.com/allure-framework/allure2/releases)

---

## 🏃 Как запускать тесты

### Запуск всех тестов
```bash
./gradlew test
```

### Запуск конкретного тестового класса
```bash
./gradlew test --tests com.example.restframework.tests.smoke.PetStoreSmokeTest
```

### Запуск с фильтром по тегу (JUnit 5 Tags)
Например, запустить только тесты с тегом `@Tag("smoke")`:
```bash
./gradlew test --tests "*SmokeTest"
# Или через свойства JUnit (если настроено в build.gradle)
```

---

## 📊 Просмотр отчета (Allure)

После запуска тестов Gradle сложит результаты в папку `build/allure-results`.

1.  **Режим реального времени (авто-обновление):**
    ```bash
    allure serve build/allure-results
    ```

2.  **Генерация статического HTML:**
    ```bash
    allure generate build/allure-results --clean -o allure_report
    allure open allure_report
    ```

---

## 👨‍💻 Автор
Создано в рамках обучения промышленной автоматизации тестирования на Java.