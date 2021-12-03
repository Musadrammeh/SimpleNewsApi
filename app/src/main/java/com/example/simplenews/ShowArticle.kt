package com.example.simplenews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.simplenews.api.RetrofitInstance
import com.example.simplenews.models.Article

class ShowArticle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_article)
        val article = intent.getSerializableExtra("article") as Article

        val imageArticle = findViewById<ImageView>(R.id.details_image)
        // imageArticle. = article.urlToImage

        val articleTitle = findViewById<TextView>(R.id.title)
        articleTitle.text = article.title

        val articleDescription = findViewById<TextView>(R.id.descriptionArticle)
        articleDescription.text = article.description

        val articleFull = findViewById<TextView>(R.id.fullArticle)
        articleFull.text = article.content

        val articleAuthor = findViewById<TextView>(R.id.authorArticle)
        articleAuthor.text = article.author

        val articleUrl = findViewById<TextView>(R.id.urlArticle)
        articleUrl.text = article.url


    }
}