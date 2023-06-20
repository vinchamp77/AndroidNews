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
package vtsen.hashnode.dev.androidnews.ui.main

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.github.vinchamp77.buildutils.BuildExt
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepositoryImpl
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState
import vtsen.hashnode.dev.androidnews.ui.main.navigation.BottomBarNav
import vtsen.hashnode.dev.androidnews.ui.main.navigation.NavGraph
import vtsen.hashnode.dev.androidnews.ui.main.topbar.TopBar
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModelFactory
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarkArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.common.PermissionDialog
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.theme.AndroidNewsTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(showReviewDialog: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val navHostController = rememberNavController()

    val articlesRepository = ArticlesRepositoryImpl.getInstance(LocalContext.current)
    val userPrefsRepository = UserPreferencesRepositoryImpl.getInstance(LocalContext.current)
    val allArticlesViewModel: AllArticlesViewModel = viewModel(
        factory = ArticlesViewModelFactory(articlesRepository, userPrefsRepository),
    )
    val unreadArticlesViewModel: UnreadArticlesViewModel = viewModel(
        factory = ArticlesViewModelFactory(articlesRepository, userPrefsRepository),
    )
    val bookmarkArticlesViewModel: BookmarkArticlesViewModel = viewModel(
        factory = ArticlesViewModelFactory(articlesRepository, userPrefsRepository),
    )

    val uiState: ArticlesUiState by allArticlesViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                navHostController,
                allArticlesViewModel,
                unreadArticlesViewModel,
                bookmarkArticlesViewModel,
                showReviewDialog,
            )
        },
        bottomBar = { BottomBarNav(navHostController) },
    ) { paddingValues ->

        NavGraph(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
            navHostController = navHostController,
            allArticlesViewModel,
            unreadArticlesViewModel,
            bookmarkArticlesViewModel,
        )
    }

    if (uiState is ArticlesUiState.Error) {
        SnackBar(
            scaffoldState,
            (uiState as ArticlesUiState.Error).msgResId,
            onDone = {
                allArticlesViewModel.clearStatus()
            },
        )
    }

    if (BuildExt.VERSION.isNotificationRuntimePermissionNeeded()) {
        PermissionDialog(Manifest.permission.POST_NOTIFICATIONS)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AndroidNewsTheme(useSystemUIController = false) {
        MainScreen(showReviewDialog = {})
    }
}
