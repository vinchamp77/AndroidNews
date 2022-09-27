package vtsen.hashnode.dev.androidnews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import vtsen.hashnode.dev.androidnews.data.repository.FakeArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {

    private lateinit var viewModel: AllArticlesViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        val repository = FakeArticlesRepositoryImpl()
        viewModel = AllArticlesViewModel(repository)
    }

    @Test
    fun allArticles_areNotNull() = runTest {

        Assert.assertNotEquals(null, viewModel.articles.first())

        delay(1000)
        Assert.assertNotEquals(null, viewModel.articles)
    }
}