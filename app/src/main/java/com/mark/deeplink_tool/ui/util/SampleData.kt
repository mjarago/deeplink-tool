package com.mark.deeplink_tool.ui.util

import com.mark.deeplink_tool.data.model.DeeplinkItem


open class SampleData {
    companion object {
        val deeplinkList = mutableListOf<DeeplinkItem>(
            DeeplinkItem(
                id = 1,
                name = "JEC",
                scheme = "ebjec",
                path = "debug",
                imageGradient = Constant.GRADIENT_MEGATRON
            ),
            DeeplinkItem(
                id = 2,
                name = "JEC Schedule",
                scheme = "ebjec",
                path = "schedulev2",
                imageGradient = Constant.GRADIENT_JSHINE
            ),
            DeeplinkItem(
                id = 3,
                name = "AAN",
                scheme = "aanconf",
                path = "debug",
                imageGradient = Constant.GRADIENT_ULTRA_VIOLET
            ),
            DeeplinkItem(
                id = 4,
                name = "Schedule",
                scheme = "aanconf",
                path = "schedulev2",
                imageGradient = Constant.GRADIENT_EVENING_NIGHT
            ),
            DeeplinkItem(
                id = 5,
                name = "Notifications",
                scheme = "aanconf",
                path = "richpush",
                imageGradient = Constant.GRADIENT_SUNKIST
            )
        )

        fun getRandomItem(): DeeplinkItem {
            val item = deeplinkList.random()
            item.id = getRandomId()
            return item
        }

        fun getRandomId(): Int = (0..Int.MAX_VALUE).random()
    }

}

