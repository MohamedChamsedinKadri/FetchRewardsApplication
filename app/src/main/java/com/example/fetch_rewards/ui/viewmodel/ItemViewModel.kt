package com.example.fetch_rewards.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetch_rewards.data.model.Item
import com.example.fetch_rewards.data.repository.ItemRepository
import com.example.fetch_rewards.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


@HiltViewModel
class ItemViewModel @Inject constructor(private val repository: ItemRepository) : ViewModel() {

    val items = mutableStateOf<List<Item>>(emptyList())
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    init {
        fetchItems()
    }
    fun fetchItems() {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                Log.d("ItemViewModel","Fetching items...")
                items.value = repository.getProcessedItems()
                Log.d("ItemViewModel","Fetched items: ${items.value.size}")
            } catch (e: Exception) {
                error.value = "Failed to fetch items: ${e.message}"
                Log.e("ItemViewModel","Error fetching items: ${e.message}")

            }finally {
                isLoading.value = false
            }
        }
    }

}