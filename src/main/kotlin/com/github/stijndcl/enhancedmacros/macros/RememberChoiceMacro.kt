package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle
import com.github.stijndcl.enhancedmacros.parseChoices
import com.intellij.ide.IdeCoreBundle
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.Ref

class RememberChoiceMacro : EnhancedPromptingMacro() {
    override fun getName() = "RememberChoice"

    override fun getDescription() = MyBundle.message("macro.choice.remember")

    override fun process(
        context: DataContext,
        args: List<String>
    ): String? {
        if (args.size < 2) {
            return null
        }

        val prompt = args[0]
        val options = parseChoices(args.drop(1)) ?: return null

        val default = RememberMacroService.getInstance(context)?.let { svc ->
            // Check that the value actually exists in the current list of choices before blindly using it as the default
            options.find { it.first == svc.state.rememberChoice[prompt] }?.first
        }

        val userInput = Ref.create<String>()
        ApplicationManager.getApplication().invokeAndWait {
            userInput.set(showChoiceBox(prompt, options.map { it.first }.toTypedArray(), default))
        }

        val picked = userInput.get() ?: throw ExecutionCancelledException()

        val associatedValue = options.find { it.first == picked }?.second ?: return null
        RememberMacroService.getInstance(context)?.state?.rememberChoice?.put(prompt, picked)
        return associatedValue
    }

    private fun showChoiceBox(label: String?, choices: Array<String>, default: String?): String? {
        return Messages.showEditableChooseDialog(
            "$label:",
            IdeCoreBundle.message("title.input"),
            Messages.getQuestionIcon(),
            choices,
            default ?: choices.first(),
            null
        )
    }

    override fun promptUser(
        dataContext: DataContext,
        label: String?,
        defaultValue: String?
    ): String? {
        TODO("Not useful here")
    }
}
