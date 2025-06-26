package com.ryvk.retrofitpractical.repositories

import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import com.ryvk.retrofitpractical.data.remote.NewsApi
import com.ryvk.retrofitpractical.main.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor (
    private val api: NewsApi
) {
    suspend fun loadNews(apiKey: String, query: String , pageSize: Int, page: Int = 1) : List<News>{
        return withContext (Dispatchers.IO){
            val jsonObject = api.doSearch(apiKey,query,pageSize.toString(),page.toString())
            Log.d("TAG", "loadNews: $jsonObject")
            if (jsonObject.has("articles")) {
                Log.d("TAG", "loadNews: has articles")
                jsonObject.getAsJsonArray("articles").map { article ->
                    Log.d("TAG", "loadNews: $article")
                    var title: String = ""
                    var description: String = ""
                    var isComplete: Boolean = false
                    if(!article.asJsonObject.get("title").isJsonNull && article.asJsonObject.get("description").isJsonNull){
                        title = article.asJsonObject.get("title").asString
                        description = "Description is not provided"
                        isComplete = true
                    }else if(!article.asJsonObject.get("title").isJsonNull && !article.asJsonObject.get("description").isJsonNull){
                        title = article.asJsonObject.get("title").asString
                        description = article.asJsonObject.get("description").asString
                        isComplete = true
                    }

                    if(isComplete){
                        return@map News(title,description)
                    }else{
                        return@map News("No title","No description")
                    }
                }
            } else {
                Log.d("TAG", "loadNews: no articles")
                emptyList()
            }
        }
    }
}