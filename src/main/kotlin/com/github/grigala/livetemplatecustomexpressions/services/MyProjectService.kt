package com.github.grigala.livetemplatecustomexpressions.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.github.grigala.livetemplatecustomexpressions.MyBundle

@Service(Service.Level.PROJECT)
class MyProjectService(project: Project) {

    init {
        thisLogger().warn(MyBundle.message("projectService", project.name))
    }

    fun getRandomNumber() = (1..100).random()
}
