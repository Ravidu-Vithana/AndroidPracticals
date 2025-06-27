package com.ryvk.paginationpractical.main

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.ryvk.paginationpractical.other.DefaultPaginator
import com.ryvk.paginationpractical.other.ScreenState
import com.ryvk.paginationpractical.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    var state = mutableStateOf(ScreenState())

    private val paginator = DefaultPaginator(
        initialKey = state.value.currentPage,
        onLoadUpdated = {
            state.value = state.value.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            try {
                val news = repository.loadNews("33c1af70fd034f4b95c3fb9ddb9db4ba", "weather",state.value.pageSize)
                if (news.isEmpty()) NewsState.Error("No news found")
                else NewsState.Success(news)
            } catch (e: Exception) {
                NewsState.Error(e.message ?: "Unknown error")
            }
        },
        getNextKey = {
            state.value.currentPage + 1
        },
        onError = {
            state.value = state.value.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state.value = state.value.copy(
                newsItems = state.value.newsItems + items,
                currentPage = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems(){
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}

data class News(val title: String,val description: String)

sealed class NewsState {
    object Loading : NewsState()
    data class Success(val news: List<News>) : NewsState()
    data class Error(val message: String) : NewsState()
}