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
        val separator = args.getOrNull(2)

        return if (separator != null) {
            val split = text.split(separator)
            val range = parseSlice(args[1], split.size) ?: return null
            split.slice(range).joinToString(separator)
        } else {
            val range = parseSlice(args[1], text.length) ?: return null
            text.substring(range)
        }
    }
}

fun parseSlice(arg: String, totalLength: Int): IntRange? {
    if (arg.isEmpty() || arg == ":") {
        return 0..<totalLength
    }

    // :end
    if (arg.startsWith(":")) {
        var end = arg.substring(1).toIntOrNull() ?: return null
        if (end < 0) {
            end = totalLength - end
        }

        return 0..<end
    }

    // start:
    if (arg.endsWith(":")) {
        val start = arg.substring(0, arg.length - 1).toIntOrNull() ?: return null

        if (start < 0 || start > totalLength) {
            return null
        }

        return start..<totalLength
    }

    // Too many colons
    val parts = arg.split(":")
    if (parts.size != 2) {
        return null
    }

    // start:end
    val start = parts[0].toIntOrNull() ?: return null
    var end = parts[1].toIntOrNull() ?: return null

    if (end < 0) {
        end = totalLength - end
    }

    return start..<end
}
