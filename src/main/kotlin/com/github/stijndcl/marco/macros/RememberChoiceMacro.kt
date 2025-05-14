package com.github.stijndcl.marco.macros

import com.github.stijndcl.marco.MyBundle
import com.github.stijndcl.marco.parseChoices
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
        if (args.size < 3) {
            return null
        }

        val cacheKey = args[0]
        val prompt = args[1]
        val options = parseChoices(args.drop(2)) ?: return null

        // Check for uniqueness
        val uniqueNames = options.map { it.first }.distinct()
        val uniqueValues = options.map { it.second }.distinct()

        if (uniqueNames.size != options.size || uniqueValues.size != options.size) {
            throw ExecutionCancelledException()
        }

        val default = RememberMacroService.getInstance(context)?.let { svc ->
            // Check that the value actually exists in the current list of choices before blindly using it as the default
            options.find { it.second == svc.state.rememberChoice[cacheKey] }?.first
        }

        val userInput = Ref.create<String>()
        ApplicationManager.getApplication().invokeAndWait {
            userInput.set(showChoiceBox(prompt, options.map { it.first }.toTypedArray(), default))
        }

        val picked = userInput.get() ?: throw ExecutionCancelledException()

        // Sadly the choice box can no longer take key-value pairs for choices,
        // so we have to manually match the picked value to the associated value
        val associatedValue = options.find { it.first == picked }?.second ?: return null
        RememberMacroService.getInstance(context)?.state?.rememberChoice?.put(cacheKey, associatedValue)
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
