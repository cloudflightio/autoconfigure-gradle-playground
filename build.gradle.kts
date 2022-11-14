plugins {
    id("java-gradle-plugin")
    kotlin("jvm")
    kotlin("plugin.serialization")
}

description = "A opinionated approach to configure a gradle project automatically by convention. It supports to automatically configure various plugins to reduce boilerplate code in gradle projects."
group = "io.cloudflight.gradle"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.kotlin.gradleplugin)

    testImplementation(libs.bundles.testImplementationDependencies)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

gradlePlugin {
    plugins {
        create("autoconfigure-settings") {
            id = "io.cloudflight.autoconfigure-settings"
            displayName = "Autoconfigure-Settings"
            description =
                "An opinionated approach to configure a gradle project automatically by convention. It supports to automatically configure various plugins to reduce boilerplate code in gradle projects."
            implementationClass = "io.cloudflight.gradle.autoconfigure.AutoConfigureSettingsPlugin"
        }
    }
}
