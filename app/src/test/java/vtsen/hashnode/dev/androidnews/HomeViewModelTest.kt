/*
 * Copyright 2023 Vincent Tsen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import vtsen.hashnode.dev.androidnews.data.repository.FakeUserPreferencesRepositoryImpl
import vtsen.hashnode.dev.androidnews.domain.usecase.AddBookmarkArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.AddReadArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.ClearArticlesStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.GetAllArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.GetArticleStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RefreshArticlesStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RemoveBookmarkArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RemoveReadArticlesUseCase
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {
    private lateinit var viewModel: AllArticlesViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        val articlesRepository = FakeArticlesRepositoryImpl()
        val userPrefsRepository = FakeUserPreferencesRepositoryImpl()
        val getAllArticlesUseCase = GetAllArticlesUseCase(articlesRepository, userPrefsRepository)

        viewModel =
            AllArticlesViewModel(
                getAllArticlesUseCase,
                GetArticleStatusUseCase(articlesRepository),
                RefreshArticlesStatusUseCase(articlesRepository),
                ClearArticlesStatusUseCase(articlesRepository),
                AddBookmarkArticlesUseCase(userPrefsRepository),
                RemoveBookmarkArticlesUseCase(userPrefsRepository),
                AddReadArticlesUseCase(userPrefsRepository),
                RemoveReadArticlesUseCase(userPrefsRepository),
            )
    }

    @Test
    fun allArticles_areNotNull() =
        runTest {
            Assert.assertNotEquals(null, viewModel.articles.first())

            delay(1000)
            Assert.assertNotEquals(null, viewModel.articles)
        }
}
