plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ktfmt)
}

group = "nl.thijsnissen"

version = "0.1.0-SNAPSHOT"

description = "My solutions to the Advent of Code puzzles in Kotlin"

repositories {
    mavenCentral()
    mavenLocal()
}

kotlin {
    jvmToolchain(25)

    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xjsr305=strict",
            "-Werror",
            "-Wextra",
            "-verbose",
            "-Xcontext-parameters",
            "-Xcontext-sensitive-resolution",
            "-Xnested-type-aliases",
        )
    }
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) { useJUnitJupiter() }

        withType<JvmTestSuite> {
            targets.all {
                testTask.configure {
                    testLogging { events("passed", "skipped", "failed") }
                    jvmArgs = listOf("-XX:+HeapDumpOnOutOfMemoryError", "-XX:+UseG1GC")
                }
            }
        }
    }
}

ktfmt { kotlinLangStyle() }
