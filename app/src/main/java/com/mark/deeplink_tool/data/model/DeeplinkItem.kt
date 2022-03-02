package com.mark.deeplink_tool.data.model

import androidx.compose.ui.graphics.Color


data class DeeplinkItem(
    val name: String,
    val scheme: String,
    val path: String = "debug",
    val imageGradient: List<Color> = listOf()
)