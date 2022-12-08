package com.itech.multimoduleapp

import android.content.Context
import com.itech.common_utils.Navigator
import com.itech.multimoduleapp.navigation.DefaultNavigator
import com.itech.multimoduleapp.room.AppDatabase
import com.itech.news_data.room.NewsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MainModule {


    @Provides
    @Singleton
    fun provideProvider(): Navigator.Provider {
        return DefaultNavigator()
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideNewsDAO(appDatabase: AppDatabase): NewsDAO {
        return appDatabase.getNewsDao()
    }

}