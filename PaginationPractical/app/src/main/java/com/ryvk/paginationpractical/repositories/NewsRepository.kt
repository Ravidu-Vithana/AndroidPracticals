package com.ryvk.paginationpractical.repositories

import android.util.Log
import com.ryvk.paginationpractical.data.remote.NewsApi
import com.ryvk.paginationpractical.main.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.collections.map

class NewsRepository @Inject constructor (
    private val api: NewsApi
) {
    suspend fun loadNews(apiKey: String, query: String , pageSize: Int, page: Int = 1) : List<News>{
        return withContext(Dispatchers.IO) {

            val jsonObject = api.doSearch(apiKey, query, pageSize.toString(), page.toString())

            if (jsonObject.has("articles")) {

                jsonObject.getAsJsonArray("articles").map { article ->

                    var title: String = ""
                    var description: String = ""
                    var isComplete: Boolean = false

                    if (!article.asJsonObject.get("title").isJsonNull && article.asJsonObject.get("description").isJsonNull) {
                        title = article.asJsonObject.get("title").asString
                        description = "Description is not provided"
                        isComplete = true
                    } else if (!article.asJsonObject.get("title").isJsonNull && !article.asJsonObject.get(
                            "description"
                        ).isJsonNull
                    ) {
                        title = article.asJsonObject.get("title").asString
                        description = article.asJsonObject.get("description").asString
                        isComplete = true
                    }

                    if (isComplete) {
                        return@map News(title, description)
                    } else {
                        return@map News("No title", "No description")
                    }
                }
            } else {
                Log.d("TAG", "loadNews: no articles")
                emptyList()
            }
        }
    }
}