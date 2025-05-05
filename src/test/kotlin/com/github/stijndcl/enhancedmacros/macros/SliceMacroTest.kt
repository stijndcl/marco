package com.github.stijndcl.enhancedmacros.macros

import org.junit.Assert.assertEquals
import org.junit.Test

class SliceMacroTest {
    @Test
    fun testParseSliceEmpty() {
        val result = parseSlice("", 10)
        assertEquals(0..<10, result)
    }

    @Test
    fun testParseSliceOnlyColon() {
        val result = parseSlice(":", 10)
        assertEquals(0..<10, result)
    }

    @Test
    fun testParseSliceOnlyBeforeColon() {
        val result = parseSlice("5:", 10)
        assertEquals(5..<10, result)
    }

    @Test
    fun testParseSliceOnlyAfterColon() {
        val result = parseSlice(":5", 10)
        assertEquals(0..<5, result)
    }

    @Test
    fun testParseSliceBoth() {
        val result = parseSlice("3:5", 10)
        assertEquals(3..<5, result)
    }

    @Test
    fun testParseSliceNegativeEnd() {
        val result = parseSlice("3:-5", 10)
        assertEquals(3..<5, result)
    }
}
