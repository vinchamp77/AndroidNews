package vtsen.hashnode.dev.androidnews.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModelFactory

class MainActivity : ComponentActivity() {

    private val repository by lazy {
        ArticlesRepositoryImpl.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                viewModel(factory = ArticlesViewModelFactory(repository)),
                useSystemUIController = true)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainActivityPreview() {
    MainScreenPreview()
}

