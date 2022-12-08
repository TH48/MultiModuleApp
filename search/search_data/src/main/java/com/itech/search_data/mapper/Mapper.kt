package com.itech.search_data.mapper

import com.itech.search_data.model.ArticleDTO
import com.itech.search_domain.model.Article

fun ArticleDTO.toDomainArticle(): Article {
    return Article(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        title = this.title ?: "",
        urlToImage = this.urlToImage ?: ""
    )
}