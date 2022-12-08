package com.itech.search_domain.repository

import com.itech.search_domain.model.Article

interface SearchRepository {

    suspend fun getSearchArticles(map: MutableMap<String, String>): List<Article>

}