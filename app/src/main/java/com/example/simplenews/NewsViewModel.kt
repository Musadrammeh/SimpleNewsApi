package com.example.simplenews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplenews.models.NewsResponse
import com.example.simplenews.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel (
    val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<com.example.simplenews.util.Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(com.example.simplenews.util.Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : com.example.simplenews.util.Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return com.example.simplenews.util.Resource.Success(resultResponse)
            }
        }
        return com.example.simplenews.util.Resource.Error(response.message())
    }
}