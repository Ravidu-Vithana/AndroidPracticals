package com.ryvk.androidfastnetworkingpractical.main

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ryvk.androidfastnetworkingpractical.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val newsStateMutable = mutableStateOf<NewsState>(NewsState.Loading)
    val newsState: State<NewsState> = newsStateMutable

    fun loadNews(context: Context) {
        newsStateMutable.value = NewsState.Loading
        repository.loadNews(context){ newsList ->
            newsStateMutable.value = if (newsList != null) {
                NewsState.Success(newsList)
            } else {
                NewsState.Error("Failed to load news")
            }
        }
    }

}

data class News(val title: String,val description: String)

sealed class NewsState {
    object Loading : NewsState()
    data class Success(val news: List<News>) : NewsState()
    data class Error(val message: String) : NewsState()
}