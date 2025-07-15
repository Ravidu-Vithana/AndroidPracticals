package com.ryvk.samplemultimoduleapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryvk.network.model.Post
import com.ryvk.network.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val posts = apiService.getPosts()
                _uiState.update { it.copy(posts = posts, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message ?: "Unknown error",
                        isLoading = false
                    )
                }
            }
        }
    }

}

data class PostUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)