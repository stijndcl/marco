package com.github.stijndcl.enhancedmacros

/**
 * Regex that can parse a single string into a list of arguments
 * From https://stackoverflow.com/a/1757107/13568999
 */
val ARGUMENT_REGEX = Regex(""",(?=(?:[^"]*"[^"]*")*[^"]*$)""")

/**
 * The macro system only allows for one argument, so we need to parse the input string ourselves.
 */
fun parseArguments(inputString: String?): List<String> {
    if (inputString.isNullOrBlank()) return emptyList()

    return inputString.trim().split(ARGUMENT_REGEX).map { it.trim('"', ' ') }
}

fun parseChoices(args: List<String>): List<Pair<String, String>>? {
    if (args.isEmpty()) {
        return null
    }

    return args.map {
        val parts = it.split(":")

        if (parts.size == 1) {
            return@map parts[0] to parts[0]
        }

        if (parts.size > 2) {
            return@parseChoices null
        }

        parts[0] to parts[1]
    }
}
