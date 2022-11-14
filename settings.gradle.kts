/*pluginManagement {
    val kotlinVersion: String? by settings
    val safeKotlinVersion = kotlinVersion ?: "1.7.20"
    plugins {
        kotlin("jvm") version safeKotlinVersion
        kotlin("kapt") version safeKotlinVersion
        kotlin("plugin.serialization") version safeKotlinVersion
    }
}*/

plugins {
    //id("io.cloudflight.autoconfigure-settings")
}

rootProject.name = "autoconfigure-gradle-playground"