package com.mark.deeplink_tool.ui.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.mark.deeplink_tool.data.model.DeeplinkItem

data class DeeplinkState(
    val name: String = "",
    val scheme: String = "",
    val path: String = "",
    val imageGradient: List<Color>? = null,
    val errorMessage: String? = "",
    val loading: Boolean = false,
    val currentEditItem: DeeplinkItem? = null,
    val deeplinkList: List<DeeplinkItem> = emptyList(),
    val isDialogOpen: Boolean = false
) {
    companion object {
        fun initialState(): DeeplinkState = DeeplinkState()
    }
}