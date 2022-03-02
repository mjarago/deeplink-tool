package com.mark.deeplink_tool.ui.util

import com.mark.deeplink_tool.data.model.DeeplinkItem


open class SampleData {
    companion object {
        val deeplinkList = mutableListOf<DeeplinkItem>(
            DeeplinkItem("JEC", "ebjec", "debug", Constant.GRADIENT_MEGATRON),
            DeeplinkItem("JEC Schedule", "ebjec", "schedulev2", Constant.GRADIENT_JSHINE),
            DeeplinkItem("AAN", "aanconf", "debug", Constant.GRADIENT_ULTRA_VIOLET               ),
            DeeplinkItem("Schedule", "aanconf", "schedulev2", Constant.GRADIENT_EVENING_NIGHT),
            DeeplinkItem("Notifications", "aanconf", "richpush", Constant.GRADIENT_SUNKIST)
        )
    }
}

