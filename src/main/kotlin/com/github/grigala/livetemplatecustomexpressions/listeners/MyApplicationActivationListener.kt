package com.github.grigala.livetemplatecustomexpressions.listeners

import com.intellij.openapi.application.ApplicationActivationListener
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.wm.IdeFrame

internal class MyApplicationActivationListener : ApplicationActivationListener {
    override fun applicationActivated(ideFrame: IdeFrame) {
        this.thisLogger().warn(ideFrame.project?.basePath ?: "could not determine project base path")
    }
}
