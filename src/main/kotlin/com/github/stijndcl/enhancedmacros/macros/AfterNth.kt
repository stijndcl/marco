package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle

class AfterNth : MacroWithMultipleArgs() {
    override fun getName() = "AfterNth"

    override fun getDescription() = MyBundle.message("macro.afternth")

    override fun process(args: List<String>): String? {
        if (args.size != 2 && args.size != 3) {
            return null
        }

        val inputString = args[0]
        val pattern = args[1]
        val n = (args.getOrNull(2) ?: "1").toIntOrNull() ?: return null

        val split = inputString.split(pattern)
        if (split.size <= n) {
            return null
        }

        return split.subList(n, split.size).joinToString(pattern)
    }
}
