package vtsen.hashnode.dev.androidnews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import vtsen.hashnode.dev.androidnews.data.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.data.remote.WebService
import vtsen.hashnode.dev.androidnews.data.repository.MainRepository
import vtsen.hashnode.dev.androidnews.ui.screens.home.HomeViewModel

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var mockViewModel: HomeViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        val repository = MainRepository(
            ArticlesDatabase.getInstance(ApplicationProvider.getApplicationContext()),
            WebService(),
        )
        viewModel = HomeViewModel(repository)
        mockViewModel = HomeViewModel(repository, useFakeData = true)
    }

    @Test
    fun allArticles_areNotNull() {

        Assert.assertNotEquals(null, mockViewModel.allArticles)

        runBlocking {
            delay(1000)
            Assert.assertNotEquals(null, viewModel.allArticles)
        }
    }
}