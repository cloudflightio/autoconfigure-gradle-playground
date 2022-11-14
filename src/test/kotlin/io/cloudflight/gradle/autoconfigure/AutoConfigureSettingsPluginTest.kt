package io.cloudflight.gradle.autoconfigure

import io.cloudflight.gradle.autoconfigure.test.util.ProjectFixture
import io.cloudflight.gradle.autoconfigure.test.util.normalizedOutput
import io.cloudflight.gradle.autoconfigure.test.util.useFixture
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class AutoConfigureSettingsPluginTest {

    @ParameterizedTest
    @MethodSource("autoConfigureSettingsArguments")
    fun `AutoConfigureSettings plugin is applied correctly`(options: TestOptions): Unit =
        autoConfigureSettingsFixture(options.fixtureName, environment = CI_ENV) {
            val result = runCleanBuild()
            println(result.normalizedOutput)
        }

    companion object {
        @JvmStatic
        fun autoConfigureSettingsArguments(): Stream<Arguments> {
            return Stream.of(
                arguments(
                    TestOptions(
                        fixtureName = "single-java-module-default"
                    )
                ),
                arguments(
                    TestOptions(
                        fixtureName = "single-kotlin-module"
                    )
                )
            )
        }

        val CI_ENV = mapOf("GITLAB_CI" to "true")
    }

    data class TestOptions(
        val fixtureName: String
    )

    private fun <T : Any> autoConfigureSettingsFixture(
        fixtureName: String,
        gradleVersion: String? = null,
        environment: Map<String, String> = emptyMap(),
        testWork: ProjectFixture .() -> T
    ): T =
        useFixture("autoconfigure-settings", fixtureName, gradleVersion, environment, testWork)
}


