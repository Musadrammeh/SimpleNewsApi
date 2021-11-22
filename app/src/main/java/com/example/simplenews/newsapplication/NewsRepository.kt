package com.example.simplenews.newsapplication

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.simplenews.models.Article
import com.example.simplenews.newsapplication.paging.NewsPagingSource
import com.example.simplenews.newsapplication.retrofit.NewsInterface

class NewsRepository (val newsInterface: NewsInterface){

    fun getAllNewsStream():LiveData<PagingData<Article>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            NewsPagingSource(newsInterface = newsInterface)
        }
    ).liveData
}