/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.8/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    id("com.gradleup.shadow") version "8.3.3"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    maven("https://maven.lavalink.dev/releases")
    maven("https://git.tomatentum.net/api/packages/tueem/maven")
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.javacord)
    implementation(libs.dotenv)
    implementation(libs.slf4j)
    implementation(libs.logback)
    implementation(libs.log4jtoslf4j)
    implementation(libs.jultoslf4j)
    implementation(libs.jackson)
    implementation(libs.lavalink)

    implementation(libs.marinaralib)
    implementation(libs.marinarajavacord)

}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

application {
    // Define the main class for the application.
    mainClass = "net.tomatentum.tomatenmusic3.App"
}


tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
