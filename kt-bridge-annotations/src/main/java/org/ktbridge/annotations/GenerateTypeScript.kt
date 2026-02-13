package org.ktbridge.annotations

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class GenerateTypeScript(
    val outputDir: String = "",
)
