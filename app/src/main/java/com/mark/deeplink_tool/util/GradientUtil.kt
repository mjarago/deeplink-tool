package com.mark.deeplink_tool.util

import androidx.compose.ui.graphics.Color

class GradientUtil {
    companion object {
        /**
         * https://uigradients.com/
         *
         */
        // Megatron
        val megatron1 = Color(0xffc6ffdd)
        val megatron2 = Color(0xfffbd786)
        val megatron3 = Color(0xfff7797d)

        // JShine
        val jshine1 = Color(0xff12c2e9)
        val jshine2 = Color(0xffc471ed)
        val jshine3 = Color(0xfff64f59)

        // Ultra Violet
        val ultraViolet1 = Color(0xff654ea3)
        val ultraViolet2 = Color(0xffeaafc8)

        // Evening Night
        val evening1 = Color(0xff005aa7)
        val evening2 = Color(0xfffffde4)

        // Sunkist
        val sunkist1 = Color(0xfff2994a)
        val sunkist2 = Color(0xfff2c94c)

        val expresso1 = Color(0xffad5389)
        val expresso2 = Color(0xff3c1053)

        val blackRose1 = Color(0xffad5389)
        val blacRose2    = Color(0xff3c1053)

        val GRADIENT_MEGATRON = listOf(megatron1, megatron2, megatron3)
        val GRADIENT_JSHINE = listOf(jshine1, jshine2, jshine3)
        val GRADIENT_ULTRA_VIOLET = listOf(ultraViolet1, ultraViolet2)
        val GRADIENT_EVENING_NIGHT = listOf(evening1, evening2)
        val GRADIENT_SUNKIST = listOf(sunkist1, sunkist2)
        val GRADIENT_EXPRESSO = listOf(expresso1, expresso2)
        val GRADIENT_BLACK_ROSE = listOf(blackRose1, blacRose2)

        val GRADIENT_LIST = listOf(
            GRADIENT_MEGATRON,
            GRADIENT_JSHINE,
            GRADIENT_ULTRA_VIOLET,
            GRADIENT_EVENING_NIGHT,
            GRADIENT_SUNKIST,
            GRADIENT_EXPRESSO,
            GRADIENT_BLACK_ROSE
        )

        var randomGradient: List<Color> = GRADIENT_LIST.random()
    }

}