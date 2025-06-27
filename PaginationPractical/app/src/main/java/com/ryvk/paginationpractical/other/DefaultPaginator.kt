package com.ryvk.paginationpractical.other

import com.ryvk.paginationpractical.main.News
import com.ryvk.paginationpractical.main.NewsState
import kotlinx.coroutines.delay

class DefaultPaginator<Key,Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest : suspend (nextKey: Key) -> NewsState,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit

) : Paginator<Key,Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val newsState = onRequest(currentKey)
        isMakingRequest = false
        delay(2000)
        when (newsState) {
            is NewsState.Success -> {
                currentKey = getNextKey(newsState.news as List<Item>)
                onSuccess(newsState.news, currentKey)
                onLoadUpdated(false)
            }

            is NewsState.Error -> {
                onError(Throwable(newsState.message))
                onLoadUpdated(false)
                return
            }
            NewsState.Loading -> {
                onLoadUpdated(true)
            }
        }

    }

    override fun reset() {
        currentKey = initialKey
    }
}

data class ScreenState(
    val isLoading : Boolean = false,
    val newsItems : List<News> = emptyList<News>(),
    val error : String? = null,
    val endReached : Boolean = false,
    val currentPage : Int = 1,
    val pageSize : Int = 10
)