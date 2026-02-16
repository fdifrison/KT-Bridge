plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {
    implementation(project(":kt-bridge-core"))
    implementation(project(":kt-bridge-annotations"))
}

gradlePlugin {
    plugins {
        create("kBridge") {
            id = "org.ktbridge.generator"
            implementationClass = "org.ktbridge.plugin.KBridgePlugin"
        }
    }
}