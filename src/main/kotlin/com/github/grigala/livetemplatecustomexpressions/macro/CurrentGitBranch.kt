package com.github.grigala.livetemplatecustomexpressions.macro

import com.intellij.codeInsight.template.*
import com.intellij.openapi.diagnostic.thisLogger
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintStream
import java.util.*


class CurrentGitBranch : Macro() {
    override fun getName(): String {
        return "currentGitBranchName"
    }

    override fun calculateResult(params: Array<out Expression>, context: ExpressionContext?): Result {
        val projectPath = context!!.project!!.basePath.toString()
        this.thisLogger().warn("Project path: $projectPath")

        val commands = arrayOf<String>("bash", "-c", "cd $projectPath && git rev-parse --abbrev-ref HEAD" )
        val process: Process = Runtime.getRuntime().exec(commands)
        process.waitFor()

        val reader = BufferedReader(InputStreamReader(process.inputStream))

        val result: String? = reader.readLine()

        this.thisLogger().warn("Result value: $result")

        reader.close()
        val branchName: String = if (result.isNullOrBlank()) "HEAD" else result
        return TextResult(branchName.split("_")[0])
    }

    private fun inheritIO(src: InputStream, dest: PrintStream) {
        Thread {
            val sc: Scanner = Scanner(src)
            while (sc.hasNextLine()) {
                println("next line: ${sc.nextLine()}");
                dest.println(sc.nextLine())
            }
        }.start()
    }
}