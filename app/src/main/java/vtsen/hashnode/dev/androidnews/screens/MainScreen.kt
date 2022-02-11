package vtsen.hashnode.dev.androidnews.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.ui.theme.AndroidNewsTheme
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel, useSystemUIController: Boolean) {
    AndroidNewsTheme(useSystemUIController = useSystemUIController) {

        val scaffoldState = rememberScaffoldState()
        Scaffold(scaffoldState = scaffoldState) {
            MainUI(viewModel)
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
private fun MainUI(viewModel: MainViewModel) {

    val articles = viewModel.articles.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items = articles.value) { article ->
            ArticleCard(
                article = article,
                onArticleCardClick = {
                    viewModel.onArticleCardClick(article)
                }
            )
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

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val viewModel = MainViewModel(LocalContext.current)
    viewModel.mockData()
    MainScreen(viewModel, useSystemUIController = false)
}
