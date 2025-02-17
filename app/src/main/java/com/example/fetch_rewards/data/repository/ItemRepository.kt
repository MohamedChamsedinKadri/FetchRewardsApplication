package com.example.fetch_rewards.data.repository

import com.example.fetch_rewards.data.model.Item
import com.example.fetch_rewards.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepository (private val apiService: ApiService){
    suspend fun getProcessedItems(): List<Item> = withContext(Dispatchers.IO) {
        val items = apiService.getItems()
        items.filter { ! it.name.isNullOrBlank() }
            .sortedWith(compareBy({ it.listId }, { it.name }))
    }
}