plugins {
    alias(libs.plugins.kotlinJvm)
}

dependencies {
    implementation(project(":kt-bridge-annotations"))

    implementation(libs.kotlin.reflect)

    implementation(libs.jakarta.validation)

}