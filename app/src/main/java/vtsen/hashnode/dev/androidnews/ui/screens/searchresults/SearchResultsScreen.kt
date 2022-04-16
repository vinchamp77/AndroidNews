package vtsen.hashnode.dev.androidnews.ui.screens.searchresults

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticlesScreen
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun SearchResultsScreen(
    viewModel: MainViewModel,
    navigateToArticle: (Int) -> Unit,
) {
    if(viewModel.searchedArticles == null) return

    ArticlesScreen(
        viewModel = viewModel,
        articles = viewModel.searchedArticles!! ,
        navigateToArticle = navigateToArticle,
        noArticlesDescStrResId = R.string.no_search_articles_desc,
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val viewModel = MainViewModel(LocalContext.current, useFakeData = true)

    SearchResultsScreen(
        viewModel,
        navigateToArticle = {})
}
