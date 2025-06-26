package com.ryvk.retrofitpractical.main

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.ryvk.retrofitpractical.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _newsState = mutableStateOf<NewsState>(NewsState.Loading)
    val newsState: State<NewsState> = _newsState

    fun loadNews() {
        _newsState.value = NewsState.Loading
        viewModelScope.launch {
            _newsState.value = try {
                val news = repository.loadNews("33c1af70fd034f4b95c3fb9ddb9db4ba", "weather",10)
                if (news.isEmpty()) NewsState.Error("No news found")
                else NewsState.Success(news)
            } catch (e: Exception) {
                NewsState.Error(e.message ?: "Unknown error")
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