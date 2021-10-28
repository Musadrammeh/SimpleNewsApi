package com.example.simplenews.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.simplenews.MainActivity
import com.example.simplenews.NewsViewModel
import com.example.simplenews.R

class SearchNewsFragment : Fragment(R.layout.fragment_searched_news) {

    lateinit var viewModel: NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }
}