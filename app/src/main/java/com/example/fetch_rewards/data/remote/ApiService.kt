package com.example.fetch_rewards.data.remote

import com.example.fetch_rewards.data.model.Item
import retrofit2.http.GET


interface  ApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>

}