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
import vtsen.hashnode.dev.androidnews.ui.screens.article.ArticleTopBar
import vtsen.hashnode.dev.androidnews.ui.screens.navigation.BottomBarNav
import vtsen.hashnode.dev.androidnews.ui.screens.navigation.BuildNavGraph
import vtsen.hashnode.dev.androidnews.ui.screens.navigation.NavRoute
import vtsen.hashnode.dev.androidnews.ui.theme.AndroidNewsTheme
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

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

    if (currentNavRoutePath.contains(NavRoute.Article.path)) {
        ArticleTopBar(navHostController, viewModel)
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

    val viewModel = MainViewModel(LocalContext.current, preview = true)

    MainScreen(
        viewModel,
        useSystemUIController = false,
    )
}