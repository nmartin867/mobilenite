package com.nmartindev.fortnite.di

import com.nmartindev.fortnite.AppConstants
import com.nmartindev.fortnite.BuildConfig
import com.nmartindev.fortnite.data.FortniteService
import com.nmartindev.fortnite.data.okhttp.RequestInterceptor
import com.nmartindev.fortnite.data.retrofit.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor(BuildConfig.API_KEY))
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BASIC) })
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.Url.FORTNITE_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideFortniteApi(retrofit: Retrofit): FortniteService = retrofit.create(FortniteService::class.java)

}
