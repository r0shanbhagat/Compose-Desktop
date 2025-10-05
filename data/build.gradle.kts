plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
}
group = project.findProperty("package") as String
version = project.findProperty("version") as String

dependencies {
    implementation(kotlin("stdlib")) // This is managed by the Kotlin plugin, so it stays as is.
    // Koin for JVM
    api(libs.koin.core)
    api(libs.koin.compose)
    // Ktor - Using the bundle
    implementation(libs.bundles.ktor.client)
    //Logging
    api(libs.logging.arbor)
}
