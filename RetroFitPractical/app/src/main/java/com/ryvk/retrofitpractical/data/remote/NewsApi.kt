package com.ryvk.retrofitpractical.data.remote

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(value = "everything")
    suspend fun doSearch(
        @Query("apiKey") key: String,
        @Query("q") query: String,
        @Query("pageSize") pageSize: String,
        @Query("page") page: String
    ) : JsonObject
}