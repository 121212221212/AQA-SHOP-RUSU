plugins {
    java
    id("io.qameta.allure") version "2.11.2"
}

group = "diplom.workspacey"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.codeborne:selenide:6.17.2")
    testImplementation("com.github.javafaker:javafaker:1.0.2")

    // Lombok - только для тестов
    testCompileOnly("org.projectlombok:lombok:1.18.28")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.28")

    testImplementation("io.qameta.allure:allure-selenide:2.24.0")
    testImplementation("mysql:mysql-connector-java:8.0.33")
    testImplementation("commons-dbutils:commons-dbutils:1.7")
}

tasks.test {
    useJUnit()

    systemProperties = mapOf(
        "selenide.headless" to "false",
        "selenide.browser" to "chrome",
        "selenide.timeout" to "30000",
        "file.encoding" to "UTF-8"
    )
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

allure {
    version.set("2.24.0")
    adapter {
        frameworks {
            junit4 {
                adapterVersion.set("2.24.0")
            }
        }
    }
}