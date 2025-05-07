package com.github.stijndcl.enhancedmacros.macros

import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service(Service.Level.PROJECT)
@State(
    name = "RememberMacroService",
    storages = [Storage("macros-remember.xml")]
)
class RememberMacroService : PersistentStateComponent<RememberMacroService.State> {
    private var myState = State()

    override fun getState() = myState
    override fun loadState(state: State) {
        myState = state
    }

    companion object {
        fun getInstance(context: DataContext): RememberMacroService? {
            return CommonDataKeys.PROJECT.getData(context)?.getService(RememberMacroService::class.java)
        }
    }

    data class State(
        @JvmField val rememberChoice: MutableMap<String, String> = mutableMapOf(),
        @JvmField val rememberText: MutableMap<String, String> = mutableMapOf()
    )
}

