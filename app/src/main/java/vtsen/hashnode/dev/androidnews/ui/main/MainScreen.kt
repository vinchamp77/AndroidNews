package vtsen.hashnode.dev.androidnews.ui.main

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.main.navigation.BottomBarNav
import vtsen.hashnode.dev.androidnews.ui.main.navigation.NavGraph
import vtsen.hashnode.dev.androidnews.ui.screens.main.navigation.TopBar
import vtsen.hashnode.dev.androidnews.ui.theme.AndroidNewsTheme
import vtsen.hashnode.dev.androidnews.ui.viewmodel.UiState
import vtsen.hashnode.dev.androidnews.ui.viewmodel.UiStateViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: UiStateViewModel,
    useSystemUIController: Boolean
) {
    AndroidNewsTheme(useSystemUIController = useSystemUIController) {

        val scaffoldState = rememberScaffoldState()
        val navHostController = rememberNavController()
        val uiState: UiState by viewModel.uiState.collectAsStateWithLifecycle()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar(navHostController) },
            bottomBar = { BottomBarNav(navHostController) }
        ) {
            NavGraph(navHostController)
        }

        if(uiState is UiState.Error) {
            SnackBar(scaffoldState, (uiState as UiState.Error).msgResId)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current)
    val viewModel = UiStateViewModel(repository)
    MainScreen(
        viewModel,
        useSystemUIController = false,
    )
}

