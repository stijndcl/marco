package com.github.stijndcl.marco.macros

import com.github.stijndcl.marco.MyBundle
import com.github.stijndcl.marco.parseChoices
import com.intellij.ide.IdeCoreBundle
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.Ref

class ChoiceMacro : EnhancedPromptingMacro() {
    override fun getName() = "Choice"

    override fun getDescription() = MyBundle.message("macro.choice")

    override fun process(
        context: DataContext,
        args: List<String>
    ): String? {
        if (args.size < 2) {
            return null
        }

        val prompt = args[0]
        val options = parseChoices(args.drop(1)) ?: return null

        val userInput = Ref.create<String>()
        ApplicationManager.getApplication().invokeAndWait {
            userInput.set(showChoiceBox(prompt, options.map { it.first }.toTypedArray()))
        }

        val picked = userInput.get() ?: throw ExecutionCancelledException()
        return options.find { it.first == picked }?.second
    }

    private fun showChoiceBox(label: String?, choices: Array<String>): String? {
        return Messages.showEditableChooseDialog(
            "$label:",
            IdeCoreBundle.message("title.input"),
            Messages.getQuestionIcon(),
            choices,
            choices.first(),
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
