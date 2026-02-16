dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}


rootProject.name = "KT-Bridge"
include("kt-bridge-core")
include("kt-bridge-annotations")
include("kt-bridge-gradle-plugin")
include("kt-bridge-test")
includeBuild("../kt-bridge-integration-test")