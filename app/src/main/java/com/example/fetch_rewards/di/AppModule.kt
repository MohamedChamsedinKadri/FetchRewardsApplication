package com.example.fetch_rewards.di

import com.example.fetch_rewards.data.remote.ApiService
import com.example.fetch_rewards.data.remote.RetrofitClient
import com.example.fetch_rewards.data.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.apiService
    }

    @Provides
    @Singleton
    fun provideItemRepository(apiService: ApiService): ItemRepository {
        return ItemRepository(apiService)
    }

}