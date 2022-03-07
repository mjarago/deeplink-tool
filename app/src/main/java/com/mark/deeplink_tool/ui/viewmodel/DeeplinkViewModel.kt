package com.mark.deeplink_tool.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mark.deeplink_tool.data.model.DeeplinkItem
import com.mark.deeplink_tool.data.repository.DeeplinkRepository
import com.mark.deeplink_tool.ui.state.DeeplinkState
import com.mark.deeplink_tool.ui.util.Util
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class DeeplinkViewModel(
    private val repository: DeeplinkRepository
) : ViewModel() {

    sealed class MainDeeplinkIntent() {
        data class LoadItems(val itemList: List<DeeplinkItem>) : MainDeeplinkIntent()
        data class AddNewItem(val item: DeeplinkItem) : MainDeeplinkIntent()
        data class UpdateItem(val item: DeeplinkItem) : MainDeeplinkIntent()
    }

    private val _state = MutableStateFlow(DeeplinkState.initialState())
    val state: StateFlow<DeeplinkState> = _state.asStateFlow()


    init {
        getDeeplinks()
    }

    fun getDeeplinks() {
        viewModelScope.launch {
            repository.getAllDeeplinks().collect {
                _state.value = _state.value.copy(deeplinkList = it)
            }
        }
    }

    fun insertDeeplink(deeplinkItem: DeeplinkItem) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            repository.insertDeeplink(deeplinkItem)
            delay((800L..1500L).random())
            _state.value = _state.value.copy(loading = false)
            onEditDone()
        }
    }


    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun onSchemeChange(scheme: String) {
        _state.value = _state.value.copy(scheme = scheme)
    }

    fun onPathChange(path: String) {
        _state.value = _state.value.copy(path = path)
    }

    fun onEditItemStarted(deeplinkItem: DeeplinkItem) {
        _state.value = _state.value.copy(
            currentEditItem = deeplinkItem,
            name = deeplinkItem.name!!,
            scheme = deeplinkItem.scheme,
            path = deeplinkItem.path
        )
    }

    fun onEditDone() {
        _state.value = _state.value.copy(currentEditItem = null)
    }

    fun validateAndSubmit(
        deeplinkItem: DeeplinkItem
    ) {
        if (deeplinkItem.scheme.isEmpty()) {
            _state.value = _state.value.copy(errorMessage = "Url scheme can't be empty")
            return
        }
        // If name is empty, just default to scheme:///path
        val finalName = if (deeplinkItem.name.isNullOrEmpty()) {
            "${deeplinkItem.scheme}:///${deeplinkItem.path}"
        } else {
            deeplinkItem.name
        }
        val deeplink = deeplinkItem.copy(name = finalName)

        insertDeeplink(deeplink)
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