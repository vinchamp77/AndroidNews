package vtsen.hashnode.dev.androidnews.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
            topBar = { TopBar(navHostController) },
            bottomBar = { BottomBarNav(navHostController) }
        ) {
            BuildNavGraph(viewModel, navHostController)
        }

        ShowSnackBar(scaffoldState, viewModel)
    }
}

@Composable
private fun TopBar(navHostController: NavHostController) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentNavRoutePath = navBackStackEntry?.destination?.route

    if(currentNavRoutePath?.contains(NavRoute.Article.path) == false) return

    IconButton(onClick = { }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null
        )
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
