package com.ryvk.headline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsItemAdapter (val newsItemList: List<NewsItem>) : RecyclerView.Adapter<NewsItemAdapter.NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card,parent,false)
        return NewsHolder(view)
    }

    override fun getItemCount(): Int {
        return newsItemList.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        var newsItem : NewsItem = newsItemList.get(position)
        holder.titleTextView.text = newsItem.title
        holder.descriptionTextView.text = newsItem.description
        holder.authorTextView.text = newsItem.author
        holder.dateTextView.text = newsItem.date
    }

    class NewsHolder(newsItem : View) : RecyclerView.ViewHolder(newsItem){
        var titleTextView : TextView = newsItem.findViewById(R.id.titleTextView)
        var descriptionTextView : TextView = newsItem.findViewById(R.id.descriptionTextView)
        var authorTextView : TextView = newsItem.findViewById(R.id.authorTextView)
        var dateTextView : TextView = newsItem.findViewById(R.id.dateTextView)
    }
}

data class NewsItem(
    var title : String?,
    var description : String?,
    var author : String?,
    var date : String?
){
    init {
        if (title == null ) title = "No title"
        if (description == null ) description = "No description"
        if (author == null ) author = "No author"
        if (date == null ) date = "No date"
    }
}