package org.ktbridge.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class KBridgePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("generateTypeScript", KBridgeTask::class.java) {
        }
    }
}