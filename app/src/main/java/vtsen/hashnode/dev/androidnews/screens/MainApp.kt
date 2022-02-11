package vtsen.hashnode.dev.androidnews.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import com.example.simplenavigationcompose.navigation.BuildNavGraph
import vtsen.hashnode.dev.androidnews.ui.theme.AndroidNewsTheme
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun MainApp(viewModel: MainViewModel, useSystemUIController: Boolean) {
    AndroidNewsTheme(useSystemUIController = useSystemUIController) {

        val scaffoldState = rememberScaffoldState()
        Scaffold(scaffoldState = scaffoldState) {
            BuildNavGraph( viewModel)
        }

        ShowSnackBar(scaffoldState, viewModel)

        DisposableEffect(LocalLifecycleOwner.current) {
            onDispose {
                viewModel.clearSnackBar()
            }
        }
    }
}

@Composable
private fun ShowSnackBar(scaffoldState: ScaffoldState, viewModel: MainViewModel) {
    viewModel.snackBarStringId?.let { stringId ->
        val msg = stringResource(stringId)
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = msg,
            )
        }
    }
}