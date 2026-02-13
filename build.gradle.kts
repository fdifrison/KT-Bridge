import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinPluginSerialization) apply false
}


allprojects {
    group = "org.ktbridge"
    version = "0.0.1"
    description = "KT-Bridge: bridging contracts to Typescript"
}

subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_25)
            freeCompilerArgs.add("-Xjsr305=strict")
        }
    }
}