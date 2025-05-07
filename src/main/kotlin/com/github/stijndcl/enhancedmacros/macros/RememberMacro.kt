package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle
import com.github.stijndcl.enhancedmacros.parseArguments
import com.intellij.ide.IdeCoreBundle
import com.intellij.ide.macro.PromptingMacro
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.Ref
import com.intellij.openapi.util.TextRange

class RememberMacro : PromptingMacro() {
    override fun getName() = "Remember"
    override fun getDescription() = MyBundle.message("macro.remember")

    override fun getRangeForSuffix(s: CharSequence, start: Int, next: Int): TextRange? {
        // Based on Macro.getRangeForSuffix
        return when (s[next]) {
            '$' -> TextRange.create(start, next + 1)
            '(' -> {
                val end = s.indexOf(")$", next)
                return if (end > 0) {
                    TextRange.create(start, end + 2)
                } else {
                    null
                }
            }

            else -> null
        }
    }

    override fun expandOccurence(context: DataContext, occurence: String): String? {
        if (occurence.isEmpty()) return null

        // Based on Macro.expandOccurence
        val parsedArgs = parseArguments(occurence.substring(occurence.indexOf("(") + 1, occurence.length - 2))
        return process(context, parsedArgs)
    }

    private fun process(context: DataContext, args: List<String>): String? {
        if (args.size != 1 && args.size != 2) {
            return null
        }

        val prompt = args[0]
        val default = RememberMacroService.instance.getCachedValue(prompt) ?: args.getOrElse(1) { "" }

        val userInput = Ref.create<String>()
        ApplicationManager.getApplication().invokeAndWait {
            userInput.set(promptUser(context, prompt, default))
        }

        val picked = userInput.get()

        // Don't overwrite old values if nothing was chosen
        if (picked == null) {
            throw ExecutionCancelledException()
        }

        RememberMacroService.instance.setCachedValue(prompt, picked)

        // Wrap multi-word strings in quotes for argument parsers
        return if (' ' in picked && !(picked.startsWith('"'))) {
            "\"$picked\""
        } else {
            picked
        }
    }

    override fun promptUser(
        dataContext: DataContext,
        label: String?,
        defaultValue: String?
    ): String? {
        return Messages.showInputDialog(
            "$label:",
            IdeCoreBundle.message("title.input"),
            Messages.getQuestionIcon(),
            defaultValue,
            null
        )
    }
}
