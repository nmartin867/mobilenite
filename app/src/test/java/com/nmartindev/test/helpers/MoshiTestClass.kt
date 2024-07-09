package com.nmartindev.test.helpers

import com.nmartindev.fortnite.data.moshi.JodaDateTimeAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

interface IMoshiTestClass {
    val moshi: Moshi
}

class MoshiTestClass: IMoshiTestClass {
    override val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(JodaDateTimeAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }
}