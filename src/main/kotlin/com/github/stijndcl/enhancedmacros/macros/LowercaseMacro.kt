package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle
import com.intellij.ide.macro.Macro
import com.intellij.ide.macro.MacroWithParams
import com.intellij.openapi.actionSystem.DataContext

class LowercaseMacro : Macro(), MacroWithParams {
    override fun getName() = "Lowercase"

    override fun getDescription() = MyBundle.message("macro.case.lower")

    override fun expand(dataContext: DataContext) = null

    override fun expand(dataContext: DataContext, vararg args: String?): String? {
        return args.first()?.lowercase()
    }
}
