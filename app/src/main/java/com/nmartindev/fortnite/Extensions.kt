package com.nmartindev.fortnite

import android.os.Build
import androidx.annotation.RequiresApi
import org.joda.time.DateTime
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun DateTime.offsetDateTime(): OffsetDateTime {
    return OffsetDateTime.ofInstant(
        Instant.ofEpochMilli(this.millis),
        ZoneOffset.ofTotalSeconds(this.zone.getOffset(this.millis) / 1000)
    )
}