package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle

class RemovePrefixMacro : MacroWithMultipleArgs() {
    override fun getName() = "RemovePrefix"
    override fun getDescription() = MyBundle.message("macro.remove.prefix")

    override fun process(args: List<String>): String? {
        if (args.size != 2) {
            return null
        }

        val inputString = args[0]
        val prefix = args[1]

        return inputString.removePrefix(prefix)
    }
}
