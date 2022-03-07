package com.mark.deeplink_tool.data.database

import androidx.room.*
import com.mark.deeplink_tool.data.model.DeeplinkItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface DeeplinkItemDao {

    @Query("SELECT * FROM deeplink")
    fun getAllDeeplinks(): Flow<List<DeeplinkItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeeplink(deeplinkItem: DeeplinkItem)

}