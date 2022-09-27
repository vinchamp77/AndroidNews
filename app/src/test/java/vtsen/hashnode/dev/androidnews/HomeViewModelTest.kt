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
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {

    private lateinit var viewModel: AllArticlesViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        val repository = ArticlesRepositoryImpl.getInstance(ApplicationProvider.getApplicationContext())
        viewModel = AllArticlesViewModel(repository)
    }

    @Test
    fun allArticles_areNotNull() {

        Assert.assertNotEquals(null, viewModel.articles)

        runBlocking {
            delay(1000)
            Assert.assertNotEquals(null, viewModel.articles)
        }
    }
}