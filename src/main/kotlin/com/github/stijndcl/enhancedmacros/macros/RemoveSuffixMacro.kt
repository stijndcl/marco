package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle
import com.github.stijndcl.enhancedmacros.parseArguments
import com.intellij.ide.macro.Macro
import com.intellij.ide.macro.MacroWithParams
import com.intellij.openapi.actionSystem.DataContext

class RemoveSuffixMacro : Macro(), MacroWithParams {
    override fun getName() = "RemoveSuffix"

    override fun getDescription() = MyBundle.message("macro.remove.suffix")

    override fun expand(dataContext: DataContext) = null

    override fun expand(dataContext: DataContext, vararg args: String?): String? {
        val parsedArgs = parseArguments(args.first())

        if (parsedArgs.size != 2) {
            return null
        }

        val inputString = parsedArgs[0]
        val prefix = parsedArgs[1]

        return inputString.removeSuffix(prefix)
    }
}
