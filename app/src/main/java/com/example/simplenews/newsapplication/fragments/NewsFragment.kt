package com.example.simplenews.newsapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenews.R
import com.example.simplenews.newsapplication.NewsViewModel
import com.example.simplenews.newsapplication.adapters.NewsPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.wait

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val viewModel by viewModels<NewsViewModel>()
    private val newsPagingAdapter=NewsPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.list.observe(viewLifecycleOwner){
            newsPagingAdapter.submitData(lifecycle,it)
        }


        newsPagingAdapter.addLoadStateListener { state->
            when(state.refresh){
                is LoadState.Loading->{
                    val progressBar = view.findViewById<ProgressBar>(R.id.news_progress).isVisible

                }
                is LoadState.NotLoading->{
                    val progressBar = view.findViewById<ProgressBar>(R.id.news_progress).isInvisible

                }
                is LoadState.Error->{
                    val progressBar = view.findViewById<ProgressBar>(R.id.news_progress).isInvisible
                    Toast.makeText(requireContext(),"Error Occured", Toast.LENGTH_SHORT).show()
                }

        }
        val newsRecycler = view.findViewById<RecyclerView>(R.id.news_recycler).adapter
    }

}
}