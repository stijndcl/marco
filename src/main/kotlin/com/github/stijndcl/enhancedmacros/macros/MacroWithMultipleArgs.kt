package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.parseArguments
import com.intellij.ide.macro.Macro
import com.intellij.ide.macro.MacroWithParams
import com.intellij.openapi.actionSystem.DataContext

abstract class MacroWithMultipleArgs : Macro(), MacroWithParams {
    override fun expand(dataContext: DataContext) = null

    override fun expand(dataContext: DataContext, vararg args: String?): String? {
        return process(parseArguments(args.first()))
    }

    abstract fun process(args: List<String>): String?
}
