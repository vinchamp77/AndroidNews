package vtsen.hashnode.dev.androidnews.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.data.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.data.remote.WebService
import vtsen.hashnode.dev.androidnews.data.repository.MainRepository
import vtsen.hashnode.dev.androidnews.ui.screens.MainScreen
import vtsen.hashnode.dev.androidnews.ui.screens.MainScreenPreview
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModel
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        val repository = MainRepository(
            ArticlesDatabase.getInstance(application),
            WebService(),
        )
        MainViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel, useSystemUIController = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MainScreenPreview()
}

