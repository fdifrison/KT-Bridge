package org.ktbridge.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.*
import org.ktbridge.core.ClassConverter
import org.ktbridge.core.ClassLoader
import org.ktbridge.core.ClassProcessor
import org.ktbridge.core.transformer.TsTransformer
import java.io.File
import java.net.URLClassLoader

abstract class KBridgeTask : DefaultTask() {

    @get:Input
    @get:Optional
    abstract val packages: ListProperty<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:InputFiles
    @get:Classpath
    abstract val classpath: ConfigurableFileCollection

    @TaskAction
    fun generate() {
        val outputFolder = outputDir.get().asFile
        if (!outputFolder.exists()) outputFolder.mkdirs()
        println("Target directory: ${outputFolder.absolutePath}")

        val inputFolder = project.layout.buildDirectory.dir("classes/kotlin/main").get().asFile

        println("Scanning directory: ${inputFolder.absolutePath}")
        val urls = (classpath.files + inputFolder).map { it.toURI().toURL() }.toTypedArray()
        println("Provided urls:$urls")
        val loader = URLClassLoader(urls, this::class.java.classLoader)


        val classes = ClassLoader(loader = loader)
            .findTargetClasses("scanner") // TODO change to list of string
        println("Found ${classes.size} classes")
        val processor = ClassProcessor(ClassConverter(), TsTransformer())
        classes.forEach { clazz ->
            println("Processing ${clazz.canonicalName}")
            val tsContent = processor.process(clazz)
            val fileName = "${clazz.simpleName}.ts"
            val outputFile = File(outputFolder, fileName)
            outputFile.writeText(tsContent)
            println("K-Bridge generated: ${outputFile.absolutePath}")
        }
    }
}