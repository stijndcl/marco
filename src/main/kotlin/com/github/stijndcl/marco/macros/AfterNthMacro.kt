package com.github.stijndcl.marco.macros

import com.github.stijndcl.marco.MyBundle

class AfterNthMacro : MacroWithMultipleArgs() {
    override fun getName() = "AfterNth"

    override fun getDescription() = MyBundle.message("macro.afternth")

    override fun process(args: List<String>): String? {
        if (args.size != 2 && args.size != 3) {
            return null
        }

        val inputString = args[0]
        val pattern = args[1]
        val n = (args.getOrNull(2) ?: "1").toIntOrNull() ?: return null

        if (n == 0) return inputString
        if (n < 0) return null

        val split = inputString.split(pattern)
        if (split.size <= n) {
            return null
        }

        return split.subList(n, split.size).joinToString(pattern)
    }
}
