package com.github.stijndcl.enhancedmacros.macros

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@Service
@State(
    name = "RememberMacroService",
    storages = [Storage("macros-remember.xml")]
)
class RememberMacroService : PersistentStateComponent<RememberMacroService.State> {
    private var myState = RememberMacroService.State()

    override fun getState() = myState
    override fun loadState(state: State) {
        myState = state
    }

    fun getCachedValue(key: String) = myState.value[key]

    fun setCachedValue(key: String, value: String) {
        myState.value[key] = value
    }

    companion object {
        val instance: RememberMacroService
            get() = ApplicationManager.getApplication().getService(RememberMacroService::class.java)

    }

    data class State(
        @JvmField val value: MutableMap<String, String> = mutableMapOf()
    )
}

