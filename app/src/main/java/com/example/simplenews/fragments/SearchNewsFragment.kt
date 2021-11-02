package com.example.simplenews.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplenews.MainActivity
import androidx.lifecycle.Observer
import com.example.simplenews.NewsViewModel
import com.example.simplenews.R
import com.example.simplenews.adapters.NewsAdapter
import com.example.simplenews.databinding.FragmentBreakingNewsBinding
import com.example.simplenews.databinding.FragmentSearchedNewsBinding
import com.example.simplenews.util.Constans.Companion.SEARCH_NEWS_TIME_DELAY
import com.example.simplenews.util.Resource



class SearchNewsFragment : Fragment(R.layout.fragment_searched_news) {

    var _binding: FragmentSearchedNewsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchedNewsBinding.inflate(inflater,container, false)

        binding.rvSearchNews

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
