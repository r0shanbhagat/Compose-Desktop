import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "2.2.0"
}

group = project.findProperty("package") as String
version = project.findProperty("version") as String

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(libs.compose.desktop)
    implementation(project(":data"))
    //Dagger
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    //JVM apps Android way
    implementation(libs.cyclone)
    // Navigation
    implementation(libs.voyager.navigator)
    implementation(libs.kotlinx.serialization)

    /**
     * Testing Dependencies
     */
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)

    // DaggerMock
    testImplementation(libs.daggermock)
    testImplementation(libs.daggermock.kotlin)

    // Mockito Core : Mockito mock objects library core API and implementation
    testImplementation(libs.mockito.core)

    // Expekt : An assertion library for Kotlin
    testImplementation(libs.expekt)

    // Kotlinx Coroutines Test : Coroutines support libraries for Kotlin
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.coroutines.swing)
    testImplementation(libs.compose.ui.test.junit4)

    //JUnit : JUnit is a unit testing framework for Java, created by Erich Gamma and Kent Beck.
    testImplementation(kotlin("test-junit5"))
}

compose.desktop {
    application {
        mainClass = "com.myapp.AppKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg)
            packageName = "myapp"
            packageVersion = "1.0.0"
            modules("java.desktop")
            macOS {
                targetFormats = setOf(TargetFormat.Dmg)
                iconFile.set(project.file("src/main/resources/drawables/launcher_icons/macos.icns"))
                bundleID = "com.myapp.desktop"
                signing {
                    sign.set(false) // Set to true and configure for notarization if you have an Apple Developer ID
                }
            }
        }
        jvmArgs += listOf("-Xmx2G", "-XX:+UseG1GC")
    }
}
