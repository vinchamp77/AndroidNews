package vtsen.hashnode.dev.androidnews.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.data.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.data.remote.WebService
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.screens.article.ArticleTopBar
import vtsen.hashnode.dev.androidnews.ui.screens.bookmarks.BookmarkedArticlesTopBar
import vtsen.hashnode.dev.androidnews.ui.screens.home.AllArticlesTopBar
import vtsen.hashnode.dev.androidnews.ui.screens.home.SearchResultsTopBar
import vtsen.hashnode.dev.androidnews.ui.screens.navigation.BottomBarNav
import vtsen.hashnode.dev.androidnews.ui.screens.navigation.BuildNavGraph
import vtsen.hashnode.dev.androidnews.ui.screens.navigation.NavRoute
import vtsen.hashnode.dev.androidnews.ui.screens.unread.UnreadArticlesTopBar
import vtsen.hashnode.dev.androidnews.ui.theme.AndroidNewsTheme
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel, useSystemUIController: Boolean) {
    AndroidNewsTheme(useSystemUIController = useSystemUIController) {

        val scaffoldState = rememberScaffoldState()
        val navHostController = rememberNavController()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar(navHostController, viewModel) },
            bottomBar = { BottomBarNav(navHostController) }
        ) {
            BuildNavGraph(viewModel, navHostController)
        }

        ShowSnackBar(scaffoldState, viewModel)
    }
}

@Composable
private fun TopBar(navHostController: NavHostController, viewModel: MainViewModel) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentNavRoutePath = navBackStackEntry?.destination?.route ?: return

    // All articles
    if (currentNavRoutePath.contains(NavRoute.Home.path)) {
        AllArticlesTopBar(navHostController, viewModel)
    // Unread articles
    } else if (currentNavRoutePath.contains(NavRoute.Unread.path)) {
        UnreadArticlesTopBar(navHostController, viewModel)
    // Bookmarked articles
    } else if (currentNavRoutePath.contains(NavRoute.Bookmarks.path)) {
        BookmarkedArticlesTopBar(navHostController, viewModel)
    // Search results articles
    } else if (currentNavRoutePath.contains(NavRoute.SearchResults.path)) {
        SearchResultsTopBar(navHostController, viewModel)
    // Single article
    } else if (currentNavRoutePath.contains(NavRoute.Article.path)) {
        ArticleTopBar(navHostController, viewModel)
    } else {
        throw Exception("Invalid navigation path!")
    }
}

@Composable
private fun ShowSnackBar(scaffoldState: ScaffoldState, viewModel: MainViewModel) {

    viewModel.showSnackBarStringId?.let { stringId ->
        val msg = stringResource(stringId)

        LaunchedEffect(viewModel.showSnackBarStringId) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = msg,
            )

            viewModel.clearShowSnackBarStringId()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    val repository = ArticlesRepositoryImpl(
        ArticlesDatabase.getInstance(LocalContext.current),
        WebService(),
    )
    val viewModel = MainViewModel(repository, useFakeData = true)

    MainScreen(
        viewModel,
        useSystemUIController = false,
    )
}
