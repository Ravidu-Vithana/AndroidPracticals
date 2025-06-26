package com.ryvk.androidfastnetworkingpractical.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ryvk.androidfastnetworkingpractical.ui.theme.AndroidFastNetworkingPracticalTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidFastNetworkingPracticalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UI(innerPadding)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UI(innerPadding: PaddingValues = PaddingValues() , viewModel: NewsViewModel = hiltViewModel()) {

    val newsState = viewModel.newsState.value
    val context = LocalContext.current

    LaunchedEffect (Unit) {
        viewModel.loadNews(context)
    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.LightGray)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                "News Today",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }
        when (val state = newsState) {
            is NewsState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Loading...")
                }
            }
            is NewsState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(state.message)
                }
            }
            is NewsState.Success -> {
                NewsList(state.news)
            }
            else -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Something went wrong")
                }
            }
        }
    }
}

@Composable
fun NewsList(news: List<News>) {
    LazyColumn {
        items (news){ item ->
            NewsCard(title = item.title, description = item.description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsCard(title: String = "Lorem Ipsum", description: String = "Lorem Ipsum Dolor Si Amet"){

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(CornerSize(10.dp)))
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(16.dp),

        ){
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Text(
            text = if(description != "null") description else "No description available",
            fontSize = 16.sp,
            color = Color.DarkGray
        )
    }

}