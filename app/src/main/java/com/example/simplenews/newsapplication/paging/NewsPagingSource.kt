package com.example.simplenews.newsapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.simplenews.models.Article
import com.example.simplenews.newsapplication.Constants
import com.example.simplenews.newsapplication.retrofit.NewsInterface
import retrofit2.HttpException
import java.io.IOException

const val STARTING_INDEX=1

class NewsPagingSource(val newsInterface: NewsInterface) : PagingSource<Int,Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position=params.key?: STARTING_INDEX

        return try{
            val data= newsInterface.getAllNews("us","business", Constants.API_KEY,position,params.loadSize)
            LoadResult.Page(
                data = data.articles!!,
                prevKey = if(params.key== STARTING_INDEX)null else position-1,
                nextKey = if(data.articles.isEmpty()) null else position+1

            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e:HttpException){
            LoadResult.Error(e)
        }


    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        TODO("Not yet implemented")
    }

}