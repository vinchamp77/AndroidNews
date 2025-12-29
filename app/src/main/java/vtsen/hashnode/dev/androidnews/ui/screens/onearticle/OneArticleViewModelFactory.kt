/*
 * Copyright 2025 Vincent Tsen
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
package vtsen.hashnode.dev.androidnews.ui.screens.onearticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository
import vtsen.hashnode.dev.androidnews.domain.usecase.AddBookmarkArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.AddReadArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.ClearArticlesStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.GetAllArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.GetArticleStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.GetOneArticleUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RefreshArticlesStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RemoveBookmarkArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RemoveReadArticlesUseCase

@Suppress("UNCHECKED_CAST")
class OneArticleViewModelFactory(
    private val articlesRepository: ArticlesRepository,
    private val userPrefsRepository: UserPreferencesRepository,
    private val articleId: String,
) : ViewModelProvider.Factory {
    private val getAllArticlesUseCase = GetAllArticlesUseCase(articlesRepository, userPrefsRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OneArticleViewModel::class.java)) {
            return OneArticleViewModel(
                GetArticleStatusUseCase(articlesRepository),
                RefreshArticlesStatusUseCase(articlesRepository),
                ClearArticlesStatusUseCase(articlesRepository),
                AddBookmarkArticlesUseCase(userPrefsRepository),
                RemoveBookmarkArticlesUseCase(userPrefsRepository),
                AddReadArticlesUseCase(userPrefsRepository),
                RemoveReadArticlesUseCase(userPrefsRepository),
                GetOneArticleUseCase(getAllArticlesUseCase),
                articleId,
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
