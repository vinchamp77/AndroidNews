package vtsen.hashnode.dev.androidnews.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepositoryImpl
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModelFactory

class MainActivity : ComponentActivity() {

    private val articlesRepository by lazy {
        ArticlesRepositoryImpl.getInstance(application)
    }

    private val userPrefsRepository by lazy {
        UserPreferencesRepositoryImpl.getInstance(application)
    }

    private val viewModel: ArticlesViewModel by viewModels {
        ArticlesViewModelFactory(articlesRepository, userPrefsRepository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        setupSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(
                viewModel,
                useSystemUIController = true)
        }
    }

    private fun setupSplashScreen() {
        var keepSplashScreenOn = true
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    keepSplashScreenOn =
                        uiState is ArticlesUiState.Loading ||
                        uiState is ArticlesUiState.Invalid
                }
            }
        }

        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreenOn
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainActivityPreview() {
    MainScreenPreview()
}

