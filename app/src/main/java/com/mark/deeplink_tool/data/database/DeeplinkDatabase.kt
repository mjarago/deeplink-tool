package com.mark.deeplink_tool.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mark.deeplink_tool.data.model.DeeplinkItem

@Database(entities = [DeeplinkItem::class], version = 1)
@TypeConverters(ColorListConverter::class)
abstract class DeeplinkDatabase: RoomDatabase() {
    abstract fun deeplinkItemDao(): DeeplinkItemDao

    companion object {
        @Volatile
        private var INSTANCE: DeeplinkDatabase? = null

        fun getDatabase(context: Context): DeeplinkDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeeplinkDatabase::class.java,
                    "deeplink_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}