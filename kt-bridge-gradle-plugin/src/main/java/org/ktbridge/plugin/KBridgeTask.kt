package org.ktbridge.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
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
    abstract var packages: List<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:InputFiles
    @get:Classpath
    abstract val classpath: ConfigurableFileCollection

    @TaskAction
    fun generate() {
        val outputFolder = outputDir.get().asFile
        println("Target directory: ${outputFolder.absolutePath}")
        val inputFolder = project.layout.buildDirectory.dir("classes/kotlin/main").get().asFile
        println("Scanning directory: ${inputFolder.absolutePath}")

        val scannerLoader = initScanner(inputFolder)
        val classes = scannerLoader.findTargetClasses(packages)
        println("Found ${classes.size} classes")

        val processor = ClassProcessor(ClassConverter(), TsTransformer())
        classes.forEach { clazz ->
            println("Processing ${clazz.canonicalName}")
            val tsContent = processor.process(clazz)
            val packageName = clazz.`package`.name
            val packagePath = packageName.replace(".", File.separator)
            val targetSubFolder = File(outputFolder, packagePath)
            if (!targetSubFolder.exists()) {
                targetSubFolder.mkdirs()
            }
            val fileName = "${clazz.simpleName}.ts"
            val outputFile = File(targetSubFolder, fileName)
            outputFile.writeText(tsContent)
            println("K-Bridge generated: ${outputFile.absolutePath}")
        }
    }

    private fun initScanner(inputFolder: File): ClassLoader {
        val urls = (classpath.files + inputFolder).map { it.toURI().toURL() }.toTypedArray()
        val loader = URLClassLoader(urls, this::class.java.classLoader)
        val scannerLoader = ClassLoader(inputFolder.absolutePath, loader)
        return scannerLoader
    }
}