package com.itech.news_domain.repository

import com.itech.news_domain.model.Article

interface NewsRepository {

    suspend fun getNewsArticle(): List<Article>

}