package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle

class SliceMacro : MacroWithMultipleArgs() {
    override fun getName() = "Slice"

    override fun getDescription() = MyBundle.message("macro.slice")

    override fun process(args: List<String>): String? {
        if (args.size != 2 && args.size != 3) {
            return null
        }

        val inputString = args[0]
        val separator = args.getOrNull(2)

        return if (separator != null) {
            val split = inputString.split(separator)
            val range = parseSlice(args[1], split.size) ?: return null
            split.slice(range).joinToString(separator)
        } else {
            val range = parseSlice(args[1], inputString.length) ?: return null
            inputString.substring(range)
        }
    }
}

fun parseSlice(arg: String, totalLength: Int): IntRange? {
    var start = -1
    var end = -1

    if (arg.isEmpty() || arg == ":") {
        start = 0
        end = totalLength
    } else if (arg.startsWith(":")) {
        // :end
        start = 0
        end = arg.substring(1).toIntOrNull() ?: return null
    } else if (arg.endsWith(":")) {
        // start:
        start = arg.substring(0, arg.length - 1).toIntOrNull() ?: return null
        end = totalLength
    } else {
        // Too many colons
        val parts = arg.split(":")
        if (parts.size != 2) {
            return null
        }

        // start:end
        start = parts[0].toIntOrNull() ?: return null
        end = parts[1].toIntOrNull() ?: return null
    }

    if (start < 0) {
        start = totalLength + start
    }

    if (end < 0) {
        end = totalLength + end
    }

    if (start < 0 || end < 0 || start >= end || start >= totalLength || end > totalLength) return null

    return start..<end
}
