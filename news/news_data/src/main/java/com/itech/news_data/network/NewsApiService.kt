package com.itech.news_data.network

import com.itech.common_utils.Constants
import com.itech.news_data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    // https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=a9dbf145baf64f0c82763cee9a049d05

    @GET("top-headlines")
    suspend fun getNewsArticles(
        @Query("country") country: String,
        @Query("category") category: String = Constants.CATEGORY,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse
}