package com.itech.news_domain.di

import com.itech.news_domain.repository.NewsRepository
import com.itech.news_domain.use_case.GetNewsArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewsDomainModule {

    @Provides
    fun provideGetNewsUsecase(newsRepository: NewsRepository): GetNewsArticleUseCase {
        return GetNewsArticleUseCase(newsRepository)
    }

}