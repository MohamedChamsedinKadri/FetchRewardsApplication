package com.example.fetch_rewards.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fetch_rewards.data.model.Item


@Composable
fun ItemList (items: List<Item>){
    val groupedItems = items.groupBy { it.listId }

    LazyColumn {
        groupedItems.forEach { (listId, listItems) ->
            item {
                Text(
                    text = "List ID: $listId",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Divider()
            }
            items(listItems) { item ->
                if (item.name != null && item.name.isNotBlank()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ){
                        Text(text = "Name: ${item.name}")
                    }
                    Divider()
                }
            }

        }
    }
}