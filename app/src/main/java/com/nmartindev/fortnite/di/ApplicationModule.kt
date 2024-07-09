package com.nmartindev.fortnite.di

import com.nmartindev.fortnite.AppConstants
import com.nmartindev.fortnite.BuildConfig
import com.nmartindev.fortnite.data.moshi.JodaDateTimeAdapter
import com.nmartindev.fortnite.data.okhttp.RequestInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(JodaDateTimeAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()
}