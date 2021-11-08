package com.example.simplenews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.simplenews.api.RetrofitInstance
import com.example.simplenews.models.Article
import com.example.simplenews.models.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var progressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNewsRepositories()




    }
    private fun getNewsRepositories(){
        RetrofitInstance
            .instance
            .getBreakingNews()
            .enqueue(object: Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    // Log the error
                    Log.e(TAG, "Error getting repos: ${t.localizedMessage}")

                    // Show an error message to the user
                    Toast.makeText(this@MainActivity, R.string.unable_to_get_repo, Toast.LENGTH_LONG).show()

                    //Hide the progressbar
                    progressBar?.visibility = View.INVISIBLE
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {

                    //Hide the progressbar
                    progressBar?.visibility = View.INVISIBLE

                    if(response.isSuccessful){

                        // Getting the list of repositories
                        val listOfArticle = response.body() as? ArrayList<Article>

                        // Passing data to the next activity
                        listOfArticle?.let {
                            // Create an intent


                            // Pass the list of news
                            //intent.putParcelableArrayListExtra(NewsRepository.KEY_REPOSITORY_DATA, it)

                            // Start the new activity
                            //startActivity(intent)
                        }

                    }else{

                        // Created a message based on the error code
                        val message = when(response.code()){
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

    companion object{
        private val TAG = MainActivity :: class.java.simpleName
    }
}

