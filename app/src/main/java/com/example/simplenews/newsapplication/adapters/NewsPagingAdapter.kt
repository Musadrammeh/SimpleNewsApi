package com.example.simplenews.newsapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simplenews.R
import com.example.simplenews.databinding.ListItemBinding
import com.example.simplenews.models.Article

class NewsPagingAdapter : PagingDataAdapter <Article, NewsPagingAdapter.MyViewHolder>(DIFF_UTIL) {

   companion object{
       val DIFF_UTIL=object: DiffUtil.ItemCallback<Article>(){
           override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
               return oldItem == newItem
           }

           override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
               return oldItem.title == newItem.title

           }
       }
   }
    inner class MyViewHolder(val viewDataBinding: ViewDataBinding):
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onBindViewHolder(holder: NewsPagingAdapter.MyViewHolder, position: Int) {
        val item=getItem(position)

        holder.viewDataBinding.setVariable(BR._all, item)

        Glide.with(holder.viewDataBinding.root).load(item!!.urlToImage)
            .into(holder.viewDataBinding.root.findViewById(R.id.image_list_item))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsPagingAdapter.MyViewHolder {
        val binding=ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}