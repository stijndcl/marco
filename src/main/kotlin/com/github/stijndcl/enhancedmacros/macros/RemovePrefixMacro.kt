package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle
import com.github.stijndcl.enhancedmacros.parseArguments
import com.intellij.ide.macro.Macro
import com.intellij.ide.macro.MacroWithParams
import com.intellij.openapi.actionSystem.DataContext

class RemovePrefixMacro : Macro(), MacroWithParams {
    override fun getName() = "RemovePrefix"

    override fun getDescription() = MyBundle.message("macro.remove.prefix")

    override fun expand(dataContext: DataContext) = null

    override fun expand(dataContext: DataContext, vararg args: String?): String? {
        val parsedArgs = parseArguments(args.first())

        if (parsedArgs.size != 2) {
            return null
        }

        val inputString = parsedArgs[0]
        val prefix = parsedArgs[1]

        return inputString.removePrefix(prefix)
    }
}
