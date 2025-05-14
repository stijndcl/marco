package com.github.stijndcl.marco.macros

import com.github.stijndcl.marco.parseArguments
import com.intellij.ide.macro.PromptingMacro
import com.intellij.ide.macro.SecondQueueExpandMacro
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.util.TextRange

abstract class EnhancedPromptingMacro : PromptingMacro(), SecondQueueExpandMacro {
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

    abstract fun process(context: DataContext, args: List<String>): String?
}
