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
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.codeborne:selenide:6.17.2")
    testImplementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation("org.projectlombok:lombok:1.18.28")
    testImplementation("io.qameta.allure:allure-selenide:2.24.0")
    testImplementation("io.qameta.allure:allure-junit4:2.24.0")

    // Добавим SLF4J чтобы убрать warning в логах
    testImplementation("org.slf4j:slf4j-simple:2.0.9")

    testAnnotationProcessor("org.projectlombok:lombok:1.18.28")
}

tasks.test {
    useJUnit()

    // Настройки для системы (правильный синтаксис для Kotlin)
    systemProperties = mapOf(
        "selenide.headless" to "false",
        "selenide.browser" to "chrome",
        "selenide.timeout" to "10000"
    )
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