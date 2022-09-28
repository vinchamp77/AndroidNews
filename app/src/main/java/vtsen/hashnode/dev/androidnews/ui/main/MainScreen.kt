package vtsen.hashnode.dev.androidnews.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.data.repository.FakeArticlesRepositoryImpl
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
        ) { paddingValues ->

            NavGraph(
                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                navHostController = navHostController,
            )
        }

        if(uiState is UiState.Error) {
            SnackBar(scaffoldState, (uiState as UiState.Error).msgResId)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    val repository = FakeArticlesRepositoryImpl()
    val viewModel = UiStateViewModel(repository)
    MainScreen(
        viewModel,
        useSystemUIController = false,
    )
}

