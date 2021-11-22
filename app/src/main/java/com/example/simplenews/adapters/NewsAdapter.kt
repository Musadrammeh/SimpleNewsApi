package com.example.simplenews.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simplenews.R

import com.example.simplenews.databinding.ItemArticlePreviewBinding
import com.example.simplenews.models.Article
import com.google.android.material.imageview.ShapeableImageView


class NewsAdapter(
    private val context:Context,
    private val articles: MutableList<Article> = mutableListOf<Article>(),
    private val listener: ClickListener? = null
) : RecyclerView.Adapter<NewsAdapter.ArticleItemViewHolder>() {
    interface ClickListener{
        fun onClickListener(item: Article)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ArticleItemViewHolder {
         val itemView = LayoutInflater.from(viewGroup.context)
             .inflate(R.layout.item_article_preview, viewGroup, false)
            return ArticleItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        val callArticle = articles[position]
        holder.onBind(callArticle, context)

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemArticlePreviewBinding.bind(itemView)

        fun onBind(article: Article, context: Context) {
            binding.tvTitle.text = article.title
            Glide.with(context).load(article.urlToImage).into(binding.ivArticleImage)
        }

    }
}