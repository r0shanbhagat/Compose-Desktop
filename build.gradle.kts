import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
    alias(libs.plugins.compose.compiler)
}

group = project.findProperty("package") as String
version = project.findProperty("version") as String

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(libs.compose.desktop)
    implementation(project(":data"))
    //JVM apps Android way
    implementation(libs.cyclone)
    // Navigation
    implementation(libs.voyager.navigator)

    /**
     ******************************* Image Loading *************************************************
     **/
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    /**
     * Testing Dependencies
     */
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.core)
    testImplementation(libs.expekt)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.coroutines.swing)
    testImplementation(libs.compose.ui.test.junit4)
    testImplementation(kotlin("test-junit5"))
}

compose.desktop {
    application {
        mainClass = "com.myapp.AppKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "myapp"
            packageVersion = "1.0.0"
            // modules("java.desktop")
            macOS {
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