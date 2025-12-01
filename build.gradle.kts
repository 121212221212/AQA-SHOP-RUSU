plugins {
    java
    id("io.qameta.allure") version "2.11.2"
}

group = "ru.netology"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Test frameworks
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.codeborne:selenide:6.17.2")

    // Test data
    testImplementation("com.github.javafaker:javafaker:1.0.2")

    // Lombok
    testCompileOnly("org.projectlombok:lombok:1.18.28")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.28")

    // Allure
    testImplementation("io.qameta.allure:allure-selenide:2.24.0")

    // Database (нужен только MySQL для проверки статусов в БД)
    testImplementation("mysql:mysql-connector-java:8.0.33")
    testImplementation("commons-dbutils:commons-dbutils:1.7") // для работы с БД
}

tasks.test {
    useJUnit()

    // УБИРАЕМ ВСЕ ПРЕДУПРЕЖДЕНИЯ И КРАСНЫЙ ТЕКСТ
    systemProperties = mapOf(
        "selenide.headless" to "false",
        "selenide.browser" to "chrome",
        "selenide.timeout" to "30000",
        "selenide.pageLoadTimeout" to "60000",
        "selenide.pollingInterval" to "500",
        "file.encoding" to "UTF-8",
        "selenide.silent" to "true",
        "webdriver.chrome.silentOutput" to "true",
        "selenide.logger" to "NONE"
    )

    jvmArgs = listOf(
        "-Dfile.encoding=UTF-8",
        "-Dconsole.encoding=UTF-8",
        "-Dwebdriver.chrome.silentOutput=true",
        "-Dselenide.silent=true",
        "-Dselenide.logger=NONE"
    )

    // УБИРАЕМ ЛИШНИЕ ЛОГИ
    testLogging {
        showStandardStreams = false
        showExceptions = true
        showCauses = true
        showStackTraces = true
        events("passed", "failed", "skipped")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    systemProperty("file.encoding", "UTF-8")
}

allure {
    version.set("2.24.0")
    adapter {
        aspectjWeaver.set(true)
        frameworks {
            junit4 {
                adapterVersion.set("2.24.0")
            }
        }
    }
}