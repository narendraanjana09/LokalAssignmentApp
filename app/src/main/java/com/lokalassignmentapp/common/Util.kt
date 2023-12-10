package com.lokalassignmentapp.common

import java.math.RoundingMode
import java.text.DecimalFormat

object Util {

    fun Int.calculateDiscount(dis:Double) : String {
        val price = this.toDouble()
        val discountPrice = price - (price*(dis/100))
        val df = DecimalFormat("#")
        df.roundingMode = RoundingMode.UP
        val discountPriceRounded = df.format(discountPrice)
        return "$discountPriceRounded"
    }
}