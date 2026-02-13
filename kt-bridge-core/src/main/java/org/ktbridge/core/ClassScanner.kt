package org.ktbridge.core

import java.io.File

class ClassScanner(private var root: File) {
    fun findClassFiles(): List<File> {
        return root.walkTopDown()
            .filter { it.isFile && it.extension == "class" }
            .toList()
    }

    fun findClassFiles(subPackageName: String = ""): List<File> {
        if (subPackageName.isNotBlank()) {
            root = root.walkTopDown()
                .first { it.isDirectory && it.name.equals(subPackageName, ignoreCase = true) }
        }
        return findClassFiles()
    }

    fun findClassFile(filename: String): List<File> {
        return listOf(File(root, filename))
    }


}
