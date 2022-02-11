package vtsen.hashnode.dev.androidnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import vtsen.hashnode.dev.androidnews.screens.MainApp
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp(viewModel, useSystemUIController = true)
        }
    }
}

