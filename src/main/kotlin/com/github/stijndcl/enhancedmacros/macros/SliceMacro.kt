package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle
import com.github.stijndcl.enhancedmacros.parseArguments
import com.intellij.ide.macro.Macro
import com.intellij.ide.macro.MacroWithParams
import com.intellij.openapi.actionSystem.DataContext

class SliceMacro : Macro(), MacroWithParams {
    override fun getName() = "Slice"

    override fun getDescription() = MyBundle.message("macro.slice")

    override fun expand(dataContext: DataContext) = null

    override fun expand(dataContext: DataContext, vararg args: String?): String? {
        val parsedArgs = parseArguments(args.first())

        if (parsedArgs.size != 2 && parsedArgs.size != 3) {
            return null
        }

        val text = parsedArgs[0]
        val range = parseSlice(parsedArgs[1]) ?: return null
        val separator = parsedArgs.getOrNull(2)

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
