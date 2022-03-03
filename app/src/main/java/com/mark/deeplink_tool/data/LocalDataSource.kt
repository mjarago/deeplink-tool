package com.mark.deeplink_tool.data

import com.mark.deeplink_tool.data.database.DeeplinkItemDao
import com.mark.deeplink_tool.data.model.DeeplinkItem
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val deeplinkItemDao: DeeplinkItemDao
) {
    suspend fun getAllDeeplinks(): Flow<List<DeeplinkItem>> {
        return deeplinkItemDao.getAllDeeplinks()
    }

    suspend fun insertDeeplink(deeplinkItem: DeeplinkItem) {
        deeplinkItemDao.insertDeeplink(deeplinkItem)
    }
}