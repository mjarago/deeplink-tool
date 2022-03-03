package com.mark.deeplink_tool.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mark.deeplink_tool.data.model.DeeplinkItem
import com.mark.deeplink_tool.data.repository.DeeplinkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class DeeplinkViewModel(
    val repository: DeeplinkRepository
): ViewModel() {

    private var _deeplinks = MutableStateFlow<List<DeeplinkItem>>(emptyList())

    val deeplinks: StateFlow<List<DeeplinkItem>>
        get() = _deeplinks

    init {
        getDeeplinks()
    }

    fun getDeeplinks() {
        viewModelScope.launch {
            repository.getAllDeeplinks().collect {
                _deeplinks.value = it
            }
        }
    }

    fun insertDeeplink(deeplinkItem: DeeplinkItem) {
        viewModelScope.launch {
            repository.insertDeeplink(deeplinkItem)
        }
    }

    companion object {
        fun provideFactory(
            deeplinkRepository: DeeplinkRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DeeplinkViewModel(deeplinkRepository) as T
            }
        }
    }
}