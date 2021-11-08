package com.example.simplenews

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenews.adapters.NewsAdapter
import com.example.simplenews.databinding.ActivityMainBinding
import com.example.simplenews.db.ArticleDataBase
import com.example.simplenews.models.Article
import com.example.simplenews.repository.NewsRepository



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.newsNavHostFragment

        val repository = NewsRepository(ArticleDataBase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(repository)
        val adapter = NewsAdapter()
        binding.recyclerView.adapter = adapter





        //adapter.setItems()



    }
}

