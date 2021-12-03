package com.example.simplenews
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
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



        newsRecyclerView =findViewById(R.id.rvBreakingNews)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.setHasFixedSize(true)
        progressBar = findViewById(R.id.paginationProgressBar)

        newArrayList = arrayListOf<Article>()
        getNewsRepositories()

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
                            newsAdapter = NewsAdapter(this@MainActivity, listOfArticle) { article ->

                                // Display full article
                                    val intent = Intent(this@MainActivity, ShowArticle::class.java)

                                    intent.putExtra("article",article)

                                    startActivity(intent)
                            }
                            // Configure the recycler view
                            newsRecyclerView?.apply {
                                adapter = newsAdapter
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context)
                            }
                        }
                    }
                }
            })
    }

}



