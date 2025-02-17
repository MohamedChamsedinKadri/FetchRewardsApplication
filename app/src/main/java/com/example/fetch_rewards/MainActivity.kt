package com.example.fetch_rewards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fetch_rewards.ui.components.ErrorMessage
import com.example.fetch_rewards.ui.components.ItemList
import com.example.fetch_rewards.ui.components.LoadingIndicator
import com.example.fetch_rewards.ui.theme.FetchRewardsTheme
import com.example.fetch_rewards.ui.viewmodel.ItemViewModel
import com.example.fetch_rewards.util.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ItemViewModel = hiltViewModel()
            val items = viewModel.items.collectAsState().value

            when (items) {
                is Resource.Success -> {
                    ItemList(items = items.data)
                }

                is Resource.Error -> {
                    ErrorMessage(message = items.message, onRetry = { viewModel.fetchItems() })
                }

                is Resource.Loading -> {
                    LoadingIndicator()
                }
            }
        }
    }
}