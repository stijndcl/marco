package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle
import com.intellij.ide.macro.Macro
import com.intellij.ide.macro.MacroWithParams
import com.intellij.openapi.actionSystem.DataContext

class UppercaseMacro : MacroWithMultipleArgs() {
    override fun getName() = "Uppercase"
    override fun getDescription() = MyBundle.message("macro.case.upper")
    override fun process(args: List<String>) = args.firstOrNull()?.uppercase()
}
