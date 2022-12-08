package com.itech.search_data.di

import com.itech.search_data.network.SearchApiService
import com.itech.search_data.repository.SearchRepoImpl
import com.itech.search_domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object SearchModule {

    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApiService {
        return retrofit.create(SearchApiService::class.java)
    }

    @Provides
    fun provideSearchRepo(searchApiService: SearchApiService): SearchRepository {
        return SearchRepoImpl(searchApiService)
    }

}