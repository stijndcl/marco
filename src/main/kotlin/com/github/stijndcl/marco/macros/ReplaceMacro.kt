package com.github.stijndcl.marco.macros

import com.github.stijndcl.marco.MyBundle

class ReplaceMacro : MacroWithMultipleArgs() {
    override fun getName() = "Replace"

    override fun getDescription() = MyBundle.message("macro.replace")

    override fun process(args: List<String>): String? {
        if (args.size != 3) {
            return null
        }

        val inputString = args[0]
        val pattern = args[1]
        val replacement = args[2]

        return inputString.replace(pattern, replacement)
    }
}
