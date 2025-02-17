package com.example.fetch_rewards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchRewardsTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val viewModel : ItemViewModel = hiltViewModel()
                    val items = viewModel.items.value
                    val isLoading = viewModel.isLoading.value
                    val error = viewModel.error.value

                    if (isLoading) {
                        LoadingIndicator()
                    } else if (error != null) {
                        ErrorMessage(message = error) {
                            viewModel.fetchItems()
                        }
                    } else{
                        ItemList(items = items)
                    }

                }
            }
        }
    }
}