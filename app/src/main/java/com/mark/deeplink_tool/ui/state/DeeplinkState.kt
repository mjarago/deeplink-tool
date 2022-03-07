package com.mark.deeplink_tool.ui.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.mark.deeplink_tool.data.model.DeeplinkItem

data class DeeplinkState(
    val name: String = "",
    val scheme: String = "",
    val path: String = "",
    val errorMessage: String? = "",
    val loading: Boolean = false,
    val currentEditItem: DeeplinkItem? = null,
    val deeplinkList: List<DeeplinkItem> = emptyList()
) {
    companion object {
        fun initialState(): DeeplinkState = DeeplinkState()
    }
}