package com.mark.deeplink_tool.data.database

import com.mark.deeplink_tool.data.model.DeeplinkItem
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val deeplinkItemDao: DeeplinkItemDao 
) {
    fun getAllDeeplinks(): Flow<List<DeeplinkItem>> {
        return deeplinkItemDao.getAllDeeplinks()
    }

    suspend fun insertDeeplink(deeplinkItem: DeeplinkItem) {
        deeplinkItemDao.insertDeeplink(deeplinkItem)
    }
}