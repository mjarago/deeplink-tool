package com.mark.deeplink_tool.data.repository

import com.mark.deeplink_tool.data.database.LocalDataSource
import com.mark.deeplink_tool.data.model.DeeplinkItem
import kotlinx.coroutines.flow.Flow


class DeeplinkRepository(
    private val localDataSource: LocalDataSource
) {
    fun getAllDeeplinks(): Flow<List<DeeplinkItem>> {
        return localDataSource.getAllDeeplinks()
    }

    suspend fun insertDeeplink(deeplinkItem: DeeplinkItem) {
        localDataSource.insertDeeplink(deeplinkItem)
    }
}