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
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var mockViewModel: MainViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        viewModel = MainViewModel(ApplicationProvider.getApplicationContext())
        mockViewModel = MainViewModel(ApplicationProvider.getApplicationContext(), useFakeData = true)
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