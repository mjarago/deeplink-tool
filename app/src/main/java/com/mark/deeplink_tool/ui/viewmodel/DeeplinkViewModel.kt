package com.mark.deeplink_tool.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mark.deeplink_tool.data.model.DeeplinkItem
import com.mark.deeplink_tool.data.repository.DeeplinkRepository
import com.mark.deeplink_tool.ui.state.DeeplinkState
import com.mark.deeplink_tool.util.GradientUtil
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
            delay(300)
            repository.insertDeeplink(deeplinkItem)
            _state.value = _state.value.copy(loading = false)
            delay(800)
        }
    }

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun onSchemeChange(scheme: String) {
        _state.value = _state.value.copy(scheme = scheme, errorMessage = null)
    }

    fun onPathChange(path: String) {
        _state.value = _state.value.copy(path = path)
    }

    fun onEditItemStarted(deeplinkItem: DeeplinkItem) {
        _state.value = _state.value.copy(
            currentEditItem = deeplinkItem,
            name = deeplinkItem.name!!,
            scheme = deeplinkItem.scheme,
            path = deeplinkItem.path,
            errorMessage = null
        )
    }

    fun onEditDone() {
        _state.value = _state.value.copy(currentEditItem = null, errorMessage = null)
    }

    fun onDismissDialog() {
        _state.value = _state.value.copy(isDialogOpen = false, imageGradient = null)
    }

    fun onOpenDialog() {
        _state.value = _state.value.copy(
            name = "",
            scheme = "",
            path = "",
            isDialogOpen = true,
            imageGradient = GradientUtil.GRADIENT_LIST.random(),
            errorMessage = null
        )
    }

    /**
     * Temporary helper function for testing
     */
    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun submitDeeplink(
        deeplinkItem: DeeplinkItem
    ) {
        validateAndSubmit(deeplinkItem = deeplinkItem, cleanup = { onDismissDialog() })
    }

    fun submitEditedDeeplink(
        editedDeeplinkItem: DeeplinkItem
    ) {
        validateAndSubmit(deeplinkItem = editedDeeplinkItem, cleanup = { onEditDone() })
    }

    fun validateAndSubmit(
        deeplinkItem: DeeplinkItem,
        cleanup: () -> Unit
    ) {
        if (deeplinkItem.scheme.isEmpty()) {
            _state.value = _state.value.copy(errorMessage = "Url scheme can't be empty")
            return
        }
        // If path is empty, just default to debug
        val finalPath = if (deeplinkItem.path.isEmpty()) {
            "debug"
        } else {
            deeplinkItem.path
        }

        // If name is empty, just default to scheme:///path
        val finalName = if (deeplinkItem.name.isNullOrEmpty()) {
            "${deeplinkItem.scheme}:///${finalPath}"
        } else {
            deeplinkItem.name
        }

        val deeplink = deeplinkItem.copy(name = finalName, path = finalPath)
        insertDeeplink(deeplink)
        cleanup()
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