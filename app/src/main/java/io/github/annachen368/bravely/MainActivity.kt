package io.github.annachen368.bravely

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.annachen368.bravely.presentation.chat.ChatScreen
import io.github.annachen368.bravely.presentation.chat.ChatViewModel
import io.github.annachen368.bravely.presentation.theme.BravelyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BravelyTheme {
                val viewModel: ChatViewModel = hiltViewModel()
                ChatScreen(viewModel)
            }
        }
    }
}