package org.ktbridge.core

import java.io.File

class ClassScanner(private var root: File) {
    fun findClassFiles(): List<File> {
        return root.walkTopDown()
            .filter { it.isFile && it.extension == "class" }
            .toList()
    }

    fun findClassFiles(root: File): List<File> {
        return root.walkTopDown()
            .filter { it.isFile && it.extension == "class" }
            .toList()
    }

    fun findClassFiles(subPackageName: List<String> = emptyList()): List<File> {
        var files: List<File> = emptyList()
        subPackageName.forEach { packageName ->
            val packageRoot = root.walkTopDown().first { it.isDirectory && it.name.equals(packageName, ignoreCase = true) }
            files = files.plus(findClassFiles(packageRoot))
            println("Found $files")
        }
        return files
    }

    fun findClassFile(filename: String): List<File> {
        return listOf(File(root, filename))
    }


}
