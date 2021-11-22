package com.example.simplenews

viewBindning
import android.content.Context

import android.content.ContentValues.TAG
main
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenews.adapters.NewsAdapter
import com.example.simplenews.api.RetrofitInstance
import com.example.simplenews.models.Article
import com.example.simplenews.models.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var progressBar: ProgressBar? = null
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Article>
    lateinit var imageId: Array<Int>
    lateinit var article : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

 viewBindning
        newsRecyclerView =findViewById(R.id.rvBreakingNews)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.setHasFixedSize(true)
        progressBar = findViewById(R.id.paginationProgressBar)

        newArrayList = arrayListOf<Article>()
        getNewsRepositories()


 main
    }

    private fun getNewsRepositories() {
        RetrofitInstance
            .instance
            .getBreakingNews()
            .enqueue(object : Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    // Log the error
                    Log.e(TAG, "Error getting repos: ${t.localizedMessage}")

                    // Show an error message to the user
                    Toast.makeText(
                        this@MainActivity,
                        R.string.unable_to_get_repo,
                        Toast.LENGTH_LONG
                    ).show()

                    //Hide the progressbar
                    progressBar?.visibility = View.INVISIBLE
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {



                    if (response.isSuccessful) {

                        //Hide the progressbar
                        progressBar?.visibility = View.GONE

                        // Getting the list of repositories
                        val listOfArticle = response.body()?.articles as? ArrayList<Article>

                        // Passing data to the next activity
                        listOfArticle?.let {
                            // Initialize the adapter
                            newsAdapter = NewsAdapter(this@MainActivity, listOfArticle){article ->
                                Toast.makeText(this@MainActivity, article.toString(), Toast.LENGTH_SHORT).show()
                            }
                            // Configure the recycler view
                            newsRecyclerView?.apply {
                                adapter = newsAdapter
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context)

                            }
                        }
 viewBindning
                    }else{


                    } else {
 main

                        // Created a message based on the error code
                        val message = when (response.code()) {
                            500 -> R.string.internal_server_error
                            401 -> R.string.unauthorized
                            403 -> R.string.forbidden
                            404 -> R.string.user_not_found
                            else -> R.string.try_another_user
                        }

                        // Show message to the user
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                        Log.e(TAG, getString(message))

                    }
                }
            })
    }

//    val listOfRepos = intent?.getParcelableArrayListExtra<Repository>(KEY_REPOSITORY_DATA)
//
//    listOfRepos?.let
//    {
//
//        val numberOfRepository = getString(R.string.number_of_repos, it.size)
//
//        findViewById<TextView>(R.id.textViewNumberOfRepos)?.text = numberOfRepository
//
//        showRepos(it)
//
//    }
//}
//
//private fun showRepos(listOfRepositories: ArrayList<Repository>) {
//
//    val recyclerViewAdapter = NewsAdapter(listOfRepositories)
//
//    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//
//    recyclerView?.apply {
//        adapter = recyclerViewAdapter
//        setHasFixedSize(true)
//        layoutManager = LinearLayoutManager(context)
//    }
//}


companion object {
    private val TAG = MainActivity::class.java.simpleName
    const val KEY_REPOSITORY_DATA = "keyRepositoryData"

}
}



