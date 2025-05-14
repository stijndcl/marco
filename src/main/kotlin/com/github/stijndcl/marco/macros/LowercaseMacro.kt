package com.github.stijndcl.marco.macros

import com.github.stijndcl.marco.MyBundle
import kotlin.text.lowercase

class LowercaseMacro : MacroWithMultipleArgs() {
    override fun getName() = "Lowercase"
    override fun getDescription() = MyBundle.message("macro.case.lower")
    override fun process(args: List<String>) = args.firstOrNull()?.lowercase()
}
