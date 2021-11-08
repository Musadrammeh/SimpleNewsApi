package com.example.simplenews.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.simplenews.R
import com.example.simplenews.databinding.ItemArticlePreviewBinding
import com.example.simplenews.models.Article


class NewsAdapter(
    private val articles: MutableList<Article> = mutableListOf<Article>(),
    private val context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleItemViewHolder(parent)
            //ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent, false)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleItemViewHolder).onBind(articles[position])

    }


    override fun getItemCount(): Int {
        return articles.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(articles: List<Article>){
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }
    inner class ArticleItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview, parent, false)
    ){
        private val binding = ItemArticlePreviewBinding.bind(itemView)

        fun onBind(article: Article){
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.url
            Glide.with(context).load(article.urlToImage).into(binding.ivArticleImage)

        }
    }
//(val itemListView: ItemArticlePreviewBinding): RecyclerView.ViewHolder(itemListView.root)
}