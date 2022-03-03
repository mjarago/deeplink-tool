package com.mark.deeplink_tool.data.repository

import com.mark.deeplink_tool.data.LocalDataSource
import com.mark.deeplink_tool.data.model.DeeplinkItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


class DeeplinkRepository(
    private val localDataSource: LocalDataSource
) {
    suspend fun getAllDeeplinks(): Flow<List<DeeplinkItem>> {
        return localDataSource.getAllDeeplinks()
    }

    suspend fun insertDeeplink(deeplinkItem: DeeplinkItem) {
        localDataSource.insertDeeplink(deeplinkItem)
    }
}