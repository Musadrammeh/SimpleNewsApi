package com.example.simplenews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simplenews.MainActivity
import com.example.simplenews.NewsViewModel
import com.example.simplenews.R
import com.example.simplenews.databinding.FragmentBreakingNewsBinding
import com.example.simplenews.databinding.FragmentSavedNewsBinding

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    var _binding: FragmentSavedNewsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedNewsBinding.inflate(inflater,container, false)

        binding.rvSavedNews

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}