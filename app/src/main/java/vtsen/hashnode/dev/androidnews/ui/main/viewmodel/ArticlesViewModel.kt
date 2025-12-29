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
package vtsen.hashnode.dev.androidnews.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState
import vtsen.hashnode.dev.androidnews.domain.usecase.AddBookmarkArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.AddReadArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.ClearArticlesStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.GetArticleStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RefreshArticlesStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RemoveBookmarkArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RemoveReadArticlesUseCase

abstract class ArticlesViewModel(
    protected val getArticleStatusUseCase: GetArticleStatusUseCase,
    protected val refreshArticlesStatusUseCase: RefreshArticlesStatusUseCase,
    protected val clearArticlesStatusUseCase: ClearArticlesStatusUseCase,
    protected val addBookmarkArticlesUseCase: AddBookmarkArticlesUseCase,
    protected val removeBookmarkArticlesUseCase: RemoveBookmarkArticlesUseCase,
    protected val addReadArticlesUseCase: AddReadArticlesUseCase,
    protected val removeReadArticlesUseCase: RemoveReadArticlesUseCase,
) : ViewModel() {
    val uiState =
        getArticleStatusUseCase().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ArticlesUiState.Success,
        )

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    protected fun setIsSearching(value: Boolean) {
        _isSearching.value = value
    }

    fun onSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun refresh() =
        viewModelScope.launch {
            refreshArticlesStatusUseCase()
        }

    fun clearStatus() = clearArticlesStatusUseCase()

    fun onReadClick(articleUi: ArticleUi) =
        viewModelScope.launch {
            if (articleUi.read) {
                removeReadArticlesUseCase(articleUi.id)
            } else {
                addReadArticlesUseCase(articleUi.id)
            }
        }

    fun onBookmarkClick(articleUi: ArticleUi) =
        viewModelScope.launch {
            if (articleUi.bookmarked) {
                removeBookmarkArticlesUseCase(articleUi.id)
            } else {
                addBookmarkArticlesUseCase(articleUi.id)
            }
        }
}
