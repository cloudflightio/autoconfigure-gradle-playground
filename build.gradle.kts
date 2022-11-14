plugins {
    id("java-gradle-plugin")
    kotlin("jvm") version("1.5.31")
}

description = "A opinionated approach to configure a gradle project automatically by convention. It supports to automatically configure various plugins to reduce boilerplate code in gradle projects."
group = "io.cloudflight.gradle"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    compileOnly(libs.kotlin.gradleplugin)
    compileOnly("com.github.spotbugs.snom:spotbugs-gradle-plugin:5.0.13")

    testImplementation(libs.bundles.testImplementationDependencies)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

gradlePlugin {
    plugins {
        create("autoconfigure-gradle") {
            id = "io.cloudflight.autoconfigure-gradle"
            displayName = "Autoconfigure-Gradle"
            description =
                "An opinionated approach to configure a gradle project automatically by convention. It supports to automatically configure various plugins to reduce boilerplate code in gradle projects."
            implementationClass = "io.cloudflight.gradle.autoconfigure.AutoConfigureGradlePlugin"
        }
        /*create("autoconfigure-settings") {
            id = "io.cloudflight.autoconfigure-settings"
            displayName = "Autoconfigure-Settings"
            description =
                "An opinionated approach to configure a gradle project automatically by convention. It supports to automatically configure various plugins to reduce boilerplate code in gradle projects."
            implementationClass = "io.cloudflight.gradle.autoconfigure.AutoConfigureSettingsPlugin"
        }*/
    }
}
