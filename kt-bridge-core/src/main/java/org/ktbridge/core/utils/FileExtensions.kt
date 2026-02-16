package org.ktbridge.core.utils

import org.ktbridge.annotations.GenerateTypeScript
import java.io.File

fun File.pathToBinaryName(root: File): String {
    val relativePath = this.relativeTo(root).path
    return relativePath
        .removeSuffix(".class")
        .replace(File.separator, ".")
}

fun List<File>.mapAndFilter(root: File, block: (String) -> Class<*>) =
    this.map { file -> block(file.pathToBinaryName(root)) }
        .filter { clazz -> clazz.isAnnotationPresent(GenerateTypeScript::class.java) }