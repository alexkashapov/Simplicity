package com.fake.simplicity.utils

import java.util.*

object ValueFormatter {
    fun formatValue(value: Any?) = when (value) {
        is Date -> value.toText()
        else -> value.toString()
    }
}