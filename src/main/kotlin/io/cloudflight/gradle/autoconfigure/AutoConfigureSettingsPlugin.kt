package io.cloudflight.gradle.autoconfigure

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainSpec
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

class AutoConfigureSettingsPlugin : Plugin<Settings> {
    override fun apply(settings: Settings) {

        val kotlinVersion = "1.7.21"
        val javaVersion = JavaLanguageVersion.of(17)


        settings.pluginManagement.plugins { p ->
            p.id("org.jetbrains.kotlin.jvm").version(kotlinVersion)
            p.id("org.jetbrains.kotlin.kapt").version(kotlinVersion)
            p.id("org.jetbrains.kotlin.plugin.serialization").version(kotlinVersion)
        }

        settings.gradle.projectsLoaded {
            it.gradle.allprojects { p ->
                p.pluginManager.withPlugin("java") {
                    p.logger.quiet("Applied JAVA in ${p.name}")
                    val javaPluginExtension = p.extensions.getByType(JavaPluginExtension::class.java)
                    javaPluginExtension.modularity.inferModulePath.set(true)
                    javaPluginExtension.toolchain.languageVersion.set(javaVersion)
                }


                p.pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                    p.afterEvaluate {
                        p.logger.quiet("Applied KOTLIN in ${p.name}")
                        p.logger.quiet(p.extensions.findByName("kotlin")!!.javaClass.classLoader.toString())
                        p.logger.quiet(this.javaClass.classLoader.toString())

                        val kotlin = p.extensions.getByName("kotlin") as KotlinProjectExtension
                        kotlin.jvmToolchain {
                            (it as JavaToolchainSpec).languageVersion.set(javaVersion)
                        }
                    }
                }
            }
        }
    }
}