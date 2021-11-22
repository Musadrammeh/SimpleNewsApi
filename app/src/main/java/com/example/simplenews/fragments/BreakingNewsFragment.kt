package com.example.simplenews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.simplenews.R
import com.example.simplenews.adapters.NewsAdapter
import com.example.simplenews.databinding.FragmentBreakingNewsBinding


class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    var _binding: FragmentBreakingNewsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreakingNewsBinding.inflate(inflater,container, false)

        binding.rvBreakingNews

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}