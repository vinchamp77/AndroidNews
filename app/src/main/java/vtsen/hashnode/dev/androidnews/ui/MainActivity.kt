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
import vtsen.hashnode.dev.androidnews.ui.screens.home.HomeViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.home.HomeViewModelFactory

class MainActivity : ComponentActivity() {

    private val repository by lazy {
        MainRepository(
            ArticlesDatabase.getInstance(application),
            WebService(),
        )
    }

    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(homeViewModel, useSystemUIController = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MainScreenPreview()
}

