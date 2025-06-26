package com.ryvk.androidfastnetworkingpractical.repositories

import android.content.Context
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.ryvk.androidfastnetworkingpractical.R
import com.ryvk.androidfastnetworkingpractical.main.News
import org.json.JSONObject
import javax.inject.Inject

class NewsRepository @Inject constructor () {

    fun loadNews(context: Context,callback: (MutableList<News>?) -> Unit){

        val baseUrl = context.resources.getString(R.string.base_url)

        AndroidNetworking.get(baseUrl)
            .addQueryParameter("q", "Weather")
            .addQueryParameter("pageSize","10")
            .addQueryParameter("page","1")
            .setTag("initialNewsLoad")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Log.d("MainActivity", "onResponse: ${response.toString()}")
                    val articles = response?.getJSONArray("articles")
                    val newsList = mutableListOf<News>()
                    if (articles != null) {
                        for (i in 0 until articles.length()) {
                            val article = articles.getJSONObject(i)
                            val title = article.getString("title")
                            val description = article.getString("description")
                            newsList.add(News(title, description))
                        }
                        callback(newsList)
                    }else{
                        callback(null)
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.e("MainActivity", "onError: $anError")
                    callback(null)
                }

            })
    }
}