package com.example.simplenews.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private val listener: ClickListener
) : RecyclerView.Adapter<NewsAdapter.ArticleItemViewHolder>() {
    interface ClickListener{
        fun onClickListener(item: Article)
    }

    class ArticleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleImage: ShapeableImageView = itemView.findViewById(R.id.ivArticleImage)
        val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription : TextView = itemView.findViewById(R.id.tvDescription)
        val tvSource : TextView = itemView.findViewById(R.id.tvSource)
        val tvPublishedAt : TextView = itemView.findViewById(R.id.tvPublishedAt)


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



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ArticleItemViewHolder {
         val itemView = LayoutInflater.from(viewGroup.context)
             .inflate(R.layout.item_article_preview, viewGroup, false)
            return ArticleItemViewHolder(itemView)
    //return ArticleItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        val callArticle = articles[position]
        holder.titleImage.setOnClickListener{
            listener.onClickListener(Article())
        }
        //holder.titleImage.setImageResource(itemCount.)

        holder.tvTitle.text = articles[position].toString()
        holder.tvDescription.text = articles[position].toString()
        holder.tvSource.text = articles[position].toString()
        holder.tvPublishedAt.text = articles[position].toString()

        holder.tvTitle.setOnClickListener{
            listener.onClickListener(Article())
        }
    }


    override fun getItemCount(): Int {
        return articles.size
    }

}