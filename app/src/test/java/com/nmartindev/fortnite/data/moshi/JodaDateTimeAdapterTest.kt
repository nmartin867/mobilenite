package com.nmartindev.fortnite.data.moshi

import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import org.junit.Assert.*

import org.junit.Test


class JodaDateTimeAdapterTest {

    private val adapter = JodaDateTimeAdapter()

    @Test
    fun toJson() {
        val dateTimeString = "2023-11-01T08:31:02+00:00"
        val dateTime = DateTime.parse(dateTimeString)

        val actual = adapter.toJson(dateTime)
        assertEquals(
            "Expected: $dateTimeString Actual: $actual",
            dateTimeString,
            actual
        )
    }

    @Test
    fun fromJson() {
        val dateTimeString = "2023-11-01T08:31:02+00:00"
        val parser = ISODateTimeFormat.dateTimeParser().withOffsetParsed()

        val expected = parser.parseDateTime(dateTimeString)
        val actual = adapter.fromJson(dateTimeString)

        assertEquals("", expected.millis, actual.millis)
    }
}