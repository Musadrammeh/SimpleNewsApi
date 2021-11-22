package com.example.simplenews.newsapplication


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.simplenews.models.Article
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    val list: LiveData<PagingData<Article>> = newsRepository.getAllNewsStream()
}