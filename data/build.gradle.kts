plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp")
}

group = project.findProperty("package") as String
version = project.findProperty("version") as String

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    api("com.ToxicBakery.logging:arbor-jvm:1.35.72")
}
