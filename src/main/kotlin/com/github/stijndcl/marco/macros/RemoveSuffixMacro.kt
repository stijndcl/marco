package com.github.stijndcl.marco.macros

import com.github.stijndcl.marco.MyBundle

class RemoveSuffixMacro : MacroWithMultipleArgs() {
    override fun getName() = "RemoveSuffix"
    override fun getDescription() = MyBundle.message("macro.remove.suffix")

    override fun process(args: List<String>): String? {
        if (args.size != 2) {
            return null
        }

        val inputString = args[0]
        val prefix = args[1]

        return inputString.removeSuffix(prefix)
    }
}
