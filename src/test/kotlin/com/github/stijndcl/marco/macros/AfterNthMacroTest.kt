package com.github.stijndcl.marco.macros

import org.junit.Assert.assertEquals
import org.junit.Test

class AfterNthMacroTest {
    @Test
    fun testDefaultArg() {
        assertEquals(
            "stijndcl/marco",
            AfterNthMacro().process(listOf("github.com/stijndcl/marco", "/"))
        )
    }

    @Test
    fun testExplicitArg() {
        assertEquals(
            "marco",
            AfterNthMacro().process(listOf("github.com/stijndcl/marco", "/", "2"))
        )
    }

    @Test
    fun testArgTooBig() {
        assertEquals(
            null,
            AfterNthMacro().process(listOf("github.com/stijndcl/marco", "/", "3"))
        )
    }
}
