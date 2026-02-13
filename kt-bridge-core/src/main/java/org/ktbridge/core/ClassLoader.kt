package org.ktbridge.core

import org.ktbridge.core.utils.mapAndFilter
import java.io.File
import java.net.URLClassLoader

class ClassLoader(rootDirPath: String = "build/classes/kotlin/main") {

    private val root = File(rootDirPath)
    private val scanner = ClassScanner(root)
    private val loader = URLClassLoader(arrayOf(root.toURI().toURL()))

    fun findTargetClasses(subPackageName: String = ""): List<Class<*>> {
        return scanner.findClassFiles(subPackageName).mapAndFilter(root, loader::loadClass)
    }

    fun findTargetClasses(): List<Class<*>> {
        return scanner.findClassFiles().mapAndFilter(root, loader::loadClass)
    }

    fun findTargetClass(fileName: String): Class<*> {
        return scanner.findClassFile(fileName).mapAndFilter(root, loader::loadClass).first()
    }


}