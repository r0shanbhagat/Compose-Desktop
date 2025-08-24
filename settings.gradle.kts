pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
        id("com.google.devtools.ksp").version(extra["ksp.version"] as String) apply false
        id("org.jetbrains.compose").version(extra["compose.version"] as String) apply false
        id("org.jetbrains.kotlin.plugin.compose").version(extra["kotlin.version"] as String) apply false
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
rootProject.name = "compose-desktop"
include("data")