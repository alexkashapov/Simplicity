package com.fake.simplicity.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toText(): String = SimpleDateFormat("dd.MM.yyyy HH:mm").format(this)