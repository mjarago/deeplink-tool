package com.mark.deeplink_tool.data.model

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mark.deeplink_tool.ui.util.Constant


@Entity(tableName = "deeplink")
data class DeeplinkItem(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "scheme") val scheme: String,
    @ColumnInfo(name = "path") val path: String = "debug",
    @ColumnInfo(name = "imageGradient") val imageGradient: List<Color> = Constant.GRADIENT_MEGATRON
)