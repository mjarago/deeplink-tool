package com.mark.deeplink_tool.data.database

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ColorListConverter {
    @TypeConverter
    fun fromColorList(colorList: List<Color>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Color>>() {}.getType()
        return gson.toJson(colorList, type)
    }

    @TypeConverter
    fun toColorList(colorString: String): List<Color>? {
        val gson = Gson()
        val type =
            object : TypeToken<List<Color?>?>() {}.type
        return gson.fromJson<List<Color>>(colorString, type)
    }

}