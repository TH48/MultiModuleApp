package com.itech.search_data.repository

import com.itech.search_data.mapper.toDomainArticle
import com.itech.search_data.network.SearchApiService
import com.itech.search_domain.model.Article
import com.itech.search_domain.repository.SearchRepository

class SearchRepoImpl(private val searchApiService: SearchApiService) : SearchRepository {
    override suspend fun getSearchArticles(map: MutableMap<String, String>): List<Article> {
        return searchApiService.getSearchArticles(map).articles.map { it.toDomainArticle() }
    }
}