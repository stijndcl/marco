package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle

class SliceMacro : MacroWithMultipleArgs() {
    override fun getName() = "Slice"

    override fun getDescription() = MyBundle.message("macro.slice")

    override fun process(args: List<String>): String? {
        if (args.size != 2 && args.size != 3) {
            return null
        }

        val text = args[0]
        val range = parseSlice(args[1]) ?: return null
        val separator = args.getOrNull(2)

        return if (separator != null) {
            text.split(separator).slice(range).joinToString { separator }
        } else {
            text.substring(range)
        }
    }
}

fun parseSlice(arg: String): IntRange? {
    if (arg.isEmpty() || arg == ":") {
        return 0..<-1
    }

    // :end
    if (arg.startsWith(":")) {
        val end = arg.substring(1).toIntOrNull() ?: return null
        return 0..<end
    }

    // start:
    if (arg.endsWith(":")) {
        val start = arg.substring(0, arg.length - 1).toIntOrNull() ?: return null
        return start..<-1
    }

    // Too many colons
    val parts = arg.split(":")
    if (parts.size != 2) {
        return null
    }

    // start:end
    val start = parts[0].toIntOrNull() ?: return null
    val end = parts[1].toIntOrNull() ?: return null
    return start..<end
}
