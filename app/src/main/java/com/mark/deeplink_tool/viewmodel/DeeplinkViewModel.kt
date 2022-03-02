package com.mark.deeplink_tool.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mark.deeplink_tool.data.model.DeeplinkItem
import com.mark.deeplink_tool.data.repository.DeeplinkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/* TODO: implement */
class DeeplinkViewModel(
    val repository: DeeplinkRepository
): ViewModel() {

    private val _deeplinks = MutableStateFlow<List<DeeplinkItem>>(emptyList())

    val deeplinks: StateFlow<List<DeeplinkItem>>
        get() = _deeplinks

    fun getDeeplinks() {
        viewModelScope.launch {
            // TODO: fetch from our repo
        }
    }
}