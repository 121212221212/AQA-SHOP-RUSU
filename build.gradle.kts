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
    testImplementation("org.projectlombok:lombok:1.18.28")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.28")

    // Allure
    testImplementation("io.qameta.allure:allure-selenide:2.24.0")
    testImplementation("io.qameta.allure:allure-junit4:2.24.0")

    // Logging (to remove SLF4J warnings)
    testImplementation("org.slf4j:slf4j-simple:2.0.9")
}

tasks.test {
    useJUnit()

    // Settings for visible browser and stability
    systemProperties = mapOf(
        "selenide.headless" to "false",
        "selenide.browser" to "chrome",
        "selenide.timeout" to "15000"
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