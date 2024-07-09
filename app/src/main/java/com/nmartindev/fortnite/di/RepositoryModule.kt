package com.nmartindev.fortnite.di

import com.nmartindev.fortnite.news.data.FortniteNewsRepository
import com.nmartindev.fortnite.news.data.IFortniteNewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    abstract fun bindNewsRepository(impl: FortniteNewsRepository): IFortniteNewsRepository
}