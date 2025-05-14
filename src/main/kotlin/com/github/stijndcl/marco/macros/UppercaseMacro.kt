package com.github.stijndcl.marco.macros

import com.github.stijndcl.marco.MyBundle

class UppercaseMacro : MacroWithMultipleArgs() {
    override fun getName() = "Uppercase"
    override fun getDescription() = MyBundle.message("macro.case.upper")
    override fun process(args: List<String>) = args.firstOrNull()?.uppercase()
}
