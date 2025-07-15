package com.ryvk.network.service

import com.ryvk.network.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts() : List<Post>
}