package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle
import com.github.stijndcl.enhancedmacros.parseArguments
import com.intellij.ide.macro.Macro
import com.intellij.ide.macro.MacroWithParams
import com.intellij.openapi.actionSystem.DataContext

class ReplaceMacro : Macro(), MacroWithParams {
    override fun getName() = "Replace"

    override fun getDescription() = MyBundle.message("macro.replace")

    override fun expand(dataContext: DataContext) = null

    override fun expand(dataContext: DataContext, vararg args: String?): String? {
        val parsedArgs = parseArguments(args.first())

        if (parsedArgs.size != 3) {
            return null
        }

        val inputString = parsedArgs[0]
        val pattern = parsedArgs[1]
        val replacement = parsedArgs[2]

        return inputString.replace(pattern, replacement)
    }
}
