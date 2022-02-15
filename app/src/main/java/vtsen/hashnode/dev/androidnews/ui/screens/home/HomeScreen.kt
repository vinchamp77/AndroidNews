package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    navigateToArticle: (Int) -> Unit,
) {

    val articles = viewModel.articles.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items = articles.value) { article ->
            ArticleCard(
                article = article,
                onArticleCardClick = navigateToArticle
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val viewModel = MainViewModel(LocalContext.current)
    viewModel.mockData()

    HomeScreen(
        viewModel,
        navigateToArticle = {})
}
