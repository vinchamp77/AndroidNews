package vtsen.hashnode.dev.androidnews.ui.main

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
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
import vtsen.hashnode.dev.androidnews.domain.usecase.*
import vtsen.hashnode.dev.androidnews.ui.main.navigation.BottomBarNav
import vtsen.hashnode.dev.androidnews.ui.main.navigation.NavGraph
import vtsen.hashnode.dev.androidnews.ui.main.topbar.TopBar
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModelFactory
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarkArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.common.PermissionsDialog
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadArticlesViewModel
import vtsen.hashnode.dev.androidnews.ui.theme.AndroidNewsTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val navHostController = rememberNavController()

    val articlesRepository = ArticlesRepositoryImpl.getInstance(LocalContext.current)
    val userPrefsRepository = UserPreferencesRepositoryImpl.getInstance(LocalContext.current)
    val allArticlesViewModel: AllArticlesViewModel = viewModel(
        factory = ArticlesViewModelFactory(articlesRepository, userPrefsRepository)
    )
    val unreadArticlesViewModel: UnreadArticlesViewModel = viewModel(
        factory = ArticlesViewModelFactory(articlesRepository, userPrefsRepository)
    )
    val bookmarkArticlesViewModel: BookmarkArticlesViewModel = viewModel(
        factory = ArticlesViewModelFactory(articlesRepository, userPrefsRepository)
    )

    val uiState: ArticlesUiState by allArticlesViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(navHostController) },
        bottomBar = { BottomBarNav(navHostController) }
    ) { paddingValues ->

        NavGraph(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
            navHostController = navHostController,
            allArticlesViewModel,
            unreadArticlesViewModel,
            bookmarkArticlesViewModel,
        )
    }

    if(uiState is ArticlesUiState.Error) {
        SnackBar(
            scaffoldState,
            (uiState as ArticlesUiState.Error).msgResId,
            onDone = {
                allArticlesViewModel.clearStatus()
            }
        )
    }

    if (BuildExt.VERSION.isRuntimePermissionSupported()) {
        PermissionsDialog(
            permission = Manifest.permission.POST_NOTIFICATIONS,
            onPermissionGranted = {},
            onPermissionDenied = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    AndroidNewsTheme(useSystemUIController = false) {
        MainScreen()
    }
}