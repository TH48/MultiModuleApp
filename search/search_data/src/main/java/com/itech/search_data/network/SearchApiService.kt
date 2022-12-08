package com.itech.search_data.network

import com.itech.search_data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface SearchApiService {

    //https://newsapi.org/v2/everything?q=tesla&from=2022-11-06&sortBy=publishedAt&apiKey=a9dbf145baf64f0c82763cee9a049d05


    @GET("everything")
    suspend fun getSearchArticles(
        @QueryMap map: MutableMap<String, String>
    ): NewsResponse

}