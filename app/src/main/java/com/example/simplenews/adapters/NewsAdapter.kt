package com.example.simplenews.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simplenews.R

import com.example.simplenews.databinding.ItemArticlePreviewBinding
import com.example.simplenews.models.Article


class NewsAdapter(
    private val articles: MutableList<Article> = mutableListOf<Article>(),
    private val context: Context
) : RecyclerView.Adapter<NewsAdapter.ArticleItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        return ArticleItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        holder.onBind(articles[position], context)
    }


    override fun getItemCount(): Int {
        return articles.size
    }
    class ArticleItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemArticlePreviewBinding.bind(itemView)

        fun onBind(article: Article, context: Context) {
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.url
            Glide.with(context).load(article.urlToImage).into(binding.ivArticleImage)

        }
        companion object{
            fun create(parent: ViewGroup): ArticleItemViewHolder{
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recycler_view_repository, parent, false)

                return ArticleItemViewHolder(view)
            }
        }
    }
}