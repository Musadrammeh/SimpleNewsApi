package com.example.simplenews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simplenews.MainActivity
import com.example.simplenews.NewsViewModel
import com.example.simplenews.R
import com.example.simplenews.databinding.ActivityMainBinding
import com.example.simplenews.databinding.FragmentArticleBinding
import java.nio.file.Paths.get

class ArticleFragment : Fragment(R.layout.fragment_article) {

    var _binding: FragmentArticleBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View  {

        _binding = FragmentArticleBinding.inflate(inflater,container, false)

        binding.webView

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}