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
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepositoryImpl
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModelFactory
import vtsen.hashnode.dev.androidnews.ui.theme.AndroidNewsTheme

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

    private val reviewManager by lazy {
        ReviewManagerFactory.create(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        setupSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            AndroidNewsTheme(useSystemUIController = true) {
                MainScreen(showReviewDialog = ::showReviewDialog)
            }
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

    private fun showReviewDialog() {
        val request = reviewManager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reviewInfo = task.result
                reviewManager.launchReviewFlow(this, reviewInfo)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainActivityPreview() {
    MainScreenPreview()
}

