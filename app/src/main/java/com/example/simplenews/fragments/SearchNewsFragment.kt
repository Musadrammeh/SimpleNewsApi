package com.example.simplenews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simplenews.R
import com.example.simplenews.databinding.FragmentSearchedNewsBinding


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
