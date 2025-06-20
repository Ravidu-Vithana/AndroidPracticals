package com.ryvk.headline

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class MainActivityViewModal {

    fun getNewsList(activity: Activity, query: String , callback: (LiveData<List<NewsItem>>)->Unit){

        val BASE_URL: String = activity.resources.getString(R.string.base_url)

        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url(BASE_URL + "everything?q=${query}&apiKey=33c1af70fd034f4b95c3fb9ddb9db4ba&pageSize=10")
            .addHeader("Content-Type", "application/json")
            .build()

        Thread {
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()

                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {

                    if(response.isSuccessful && response.body != null){

//                        Log.d("TAG", "onResponse: "+response.body?.string())

                        val listType = object : TypeToken<JsonObject>() {}.type
                        val gson = Gson()
                        var newsListLiveData = MutableLiveData<List<NewsItem>>()
                        val jsonObject = gson.fromJson(response.body!!.string(),JsonObject::class.java)

                        Log.d("TAG", "onResponse: ${jsonObject.get("status")}")
                        Log.d("TAG", "onResponse: ${jsonObject.get("totalResults")}")
                        Log.d("TAG", "onResponse: ${jsonObject.get("articles")}")

                        var newsList = ArrayList<NewsItem>()

                        val jsonArray = jsonObject.get("articles").asJsonArray
                        jsonArray.forEach{item ->
                            val newsItemObject = item.asJsonObject
                            val title =if(!newsItemObject.get("title").isJsonNull) newsItemObject.get("title").asString else null
                            val author = if(!newsItemObject.get("author").isJsonNull) newsItemObject.get("author").asString else null
                            val description =if(!newsItemObject.get("description").isJsonNull) newsItemObject.get("description").asString else null
                            val date =if(!newsItemObject.get("publishedAt").isJsonNull) newsItemObject.get("publishedAt").asString.split("T")[0] else null
                            newsList.add(NewsItem(title,description,author,date))
                        }

                        activity.runOnUiThread{
                            newsListLiveData.value = newsList
                            callback(newsListLiveData)
                        }
                    }
                }
            })
        }.start()

    }

}