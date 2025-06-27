package com.ryvk.paginationpractical.other

interface Paginator<Key,Item> {
    suspend fun loadNextItems()
    fun reset()
}