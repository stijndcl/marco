package com.github.stijndcl.enhancedmacros.macros

import com.github.stijndcl.enhancedmacros.MyBundle
import com.intellij.ide.macro.Macro
import com.intellij.openapi.actionSystem.DataContext
import kotlinx.datetime.Clock

class UnixNowMacro : Macro() {
    override fun getName() = "UnixNow"

    override fun getDescription() = MyBundle.message("macro.timestamp.unix")

    override fun expand(context: DataContext) = Clock.System.now().epochSeconds.toString()

    override fun preview(context: DataContext) = expand(context)
}
