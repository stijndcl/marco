package com.github.stijndcl.marco

import org.junit.Assert.assertEquals
import org.junit.Test

class ParsingTest {
    @Test
    fun testParseEmptyArguments() {
        assertEquals(emptyList<String>(), parseArguments(""))
    }

    @Test
    fun testParseBasic() {
        assertEquals(listOf("a", "b", "c"), parseArguments("a,b,c"))
    }

    @Test
    fun testParseWhitespaceTrimmed() {
        assertEquals(listOf("a", "b", "c"), parseArguments("a, b, c"))
    }

    @Test
    fun testParseQuotedArguments() {
        assertEquals(listOf("a", "b", "c, with a comma"), parseArguments("a, \"b\", \"c, with a comma\""))
    }
}
