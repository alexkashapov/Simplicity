package com.fake.simplicity.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toText(): String = SimpleDateFormat("dd.MM.yyyy HH:mm").format(this)

fun Double.toTimeText(): String {
    val hours = (this / 60).toInt()
    val minutes = (this % 60).toInt()
    return "$hours : $minutes"
}