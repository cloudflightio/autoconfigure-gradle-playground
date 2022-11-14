package io.cloudflight.gradle.autoconfigure

import com.github.spotbugs.snom.Confidence
import com.github.spotbugs.snom.SpotBugsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainSpec
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

class AutoConfigureGradlePlugin : Plugin<Project> {
    override fun apply(p: Project) {
        p.pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
            p.logger.quiet("Applied KOTLIN in ${p.name}")
            val kotlinExtension = p.extensions.getByName("kotlin")

            p.logger.quiet(kotlinExtension.toString())
            p.logger.quiet(kotlinExtension.javaClass.toString())
            p.logger.quiet(kotlinExtension.javaClass.classLoader.toString())
            p.logger.quiet(this.javaClass.classLoader.toString())


            val kotlin = kotlinExtension as KotlinProjectExtension
            kotlin.jvmToolchain {
                (it as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(17))
            }
        }
        p.pluginManager.withPlugin("com.github.spotbugs") {
            p.logger.quiet("Applied SpotBugs in ${p.name}")
            p.logger.quiet(p.extensions.getByName("spotbugs").javaClass.toString())
            p.logger.quiet(p.extensions.getByName("spotbugs").javaClass.classLoader.toString())
            val extension = p.extensions.getByName("spotbugs") as SpotBugsExtension
            extension.reportLevel.set(Confidence.HIGH)
        }
    }
}