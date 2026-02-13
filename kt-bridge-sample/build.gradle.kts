plugins {
    alias(libs.plugins.kotlinJvm)
}

dependencies {
    implementation(project(":kt-bridge-annotations"))
    implementation(project(":kt-bridge-core"))

    testImplementation(kotlin("test"))
}