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
package vtsen.hashnode.dev.androidnews.domain.mapper

import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepoStatus
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState

fun ArticlesRepoStatus.toArticlesUiState(): ArticlesUiState =
    when (this) {
        is ArticlesRepoStatus.Invalid -> ArticlesUiState.Invalid
        is ArticlesRepoStatus.IsLoading -> ArticlesUiState.Loading
        is ArticlesRepoStatus.Success -> ArticlesUiState.Success
        is ArticlesRepoStatus.Fail -> ArticlesUiState.Error(R.string.no_internet)
    }
