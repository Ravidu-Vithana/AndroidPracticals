package com.ryvk.samplemultimoduleapp.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryvk.samplemultimoduleapp.main.composables.PostList
import com.ryvk.samplemultimoduleapp.ui.theme.SampleMultiModuleAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryvk.samplemultimoduleapp.main.composables.ErrorMessage
import com.ryvk.samplemultimoduleapp.main.composables.FullScreenLoader

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleMultiModuleAppTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()

    when {
        state.isLoading -> FullScreenLoader()
        state.error != null -> ErrorMessage(state.error!!) { viewModel.loadPosts() }
        else -> PostList(state.posts)
    }
}
