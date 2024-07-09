package com.nmartindev.fortnite.data.moshi

import android.os.Build
import androidx.annotation.RequiresApi
import com.nmartindev.fortnite.offsetDateTime
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.time.format.DateTimeFormatter


class JodaDateTimeAdapter {
    @RequiresApi(Build.VERSION_CODES.O)
    @ToJson fun toJson(dateTime: DateTime): String {
        return dateTime.offsetDateTime().format(
            DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssxxx")
        )
    }

    @FromJson fun fromJson(dateTime: String): DateTime {
        val parser = ISODateTimeFormat.dateTimeParser().withOffsetParsed()
        return parser.parseDateTime(dateTime)
    }
}