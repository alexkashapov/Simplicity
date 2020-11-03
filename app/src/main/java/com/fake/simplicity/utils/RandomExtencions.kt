package com.fake.simplicity.utils

import com.fake.simplicity.data.Event
import com.fake.simplicity.data.Move
import com.fake.simplicity.data.Notice
import java.util.*

fun Random.randomEvent(): Event = Event(
    startTime = Date(nextLong()),
    endTime = Date(nextLong()),
    name = UUID.randomUUID().toString()
)

fun Random.randomNotice(): Notice = Notice(
    flightDate = Date(nextLong()),
    gate = UUID.randomUUID().toString()
)


fun Random.randomMove(): Move = Move(
    fromPlace = UUID.randomUUID().toString(),
    toPlace = UUID.randomUUID().toString(),
    estimateTime = nextDouble()
)


