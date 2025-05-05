package com.github.stijndcl.enhancedmacros.macros

import org.junit.Assert.assertEquals
import org.junit.Test

class AfterNthMacroTest {
    @Test
    fun testDefaultArg() {
        assertEquals(
            "stijndcl/jb-enhanced-macros",
            AfterNthMacro().process(listOf("github.com/stijndcl/jb-enhanced-macros", "/"))
        )
    }

    @Test
    fun testExplicitArg() {
        assertEquals(
            "jb-enhanced-macros",
            AfterNthMacro().process(listOf("github.com/stijndcl/jb-enhanced-macros", "/", "2"))
        )
    }

    @Test
    fun testArgTooBig() {
        assertEquals(
            null,
            AfterNthMacro().process(listOf("github.com/stijndcl/jb-enhanced-macros", "/", "3"))
        )
    }
}
