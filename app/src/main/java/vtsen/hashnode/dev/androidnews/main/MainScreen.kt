package vtsen.hashnode.dev.androidnews.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.ui.theme.AndroidNewsTheme

@Composable
fun MainScreen(viewModel: MainViewModel, useSystemUIController: Boolean) {
    AndroidNewsTheme(useSystemUIController = useSystemUIController) {
        MainUI(viewModel)
    }
}

@Composable
fun MainUI(viewModel: MainViewModel) {

    val articles = viewModel.articles.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items = articles.value) { article ->
            ArticleCard(article = article)
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
