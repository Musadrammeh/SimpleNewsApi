package com.example.simplenews.repository

import com.example.simplenews.api.RetrofitInstance
import com.example.simplenews.db.ArticleDataBase


class NewsRepository(
    val db: ArticleDataBase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}