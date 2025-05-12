package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle
import com.intellij.ide.IdeCoreBundle
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.Ref

class RememberMacro : EnhancedPromptingMacro() {
    override fun getName() = "Remember"
    override fun getDescription() = MyBundle.message("macro.remember")

    override fun process(context: DataContext, args: List<String>): String? {
        if (args.size != 2 && args.size != 3) {
            return null
        }

        val cacheKey = args[0]
        val prompt = args[1]
        val default =
            RememberMacroService.getInstance(context)?.state?.rememberText?.get(cacheKey) ?: args.getOrElse(2) { "" }

        val userInput = Ref.create<String?>()
        ApplicationManager.getApplication().invokeAndWait {
            userInput.set(promptUser(context, prompt, default))
        }

        val picked = userInput.get() ?: throw ExecutionCancelledException()

        RememberMacroService.getInstance(context)?.state?.rememberText?.put(cacheKey, picked)

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
