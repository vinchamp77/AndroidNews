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

    val articlesState = viewModel.articlesStateFlow.collectAsState()
    if(articlesState.value == null) {
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items = articlesState.value!!) { article ->
            ArticleCard(
                article = article,
                onArticleCardClick = navigateToArticle,
                onBookmarkClick = viewModel::onBookmarkClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val viewModel = MainViewModel(LocalContext.current, preview = true)

    HomeScreen(
        viewModel,
        navigateToArticle = {})
}
