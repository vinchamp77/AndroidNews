package vtsen.hashnode.dev.androidnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import vtsen.hashnode.dev.androidnews.main.MainScreen
import vtsen.hashnode.dev.androidnews.main.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel, useSystemUIController = true)
        }
    }
}
