package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticlesScreen
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    navigateToArticle: (Int) -> Unit,
) {
    if(viewModel.allArticles == null) return

    ArticlesScreen(
        viewModel = viewModel,
        articles = viewModel.allArticles!! ,
        navigateToArticle = navigateToArticle,
        noArticlesDescStrResId = R.string.no_articles_desc,
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val viewModel = MainViewModel(LocalContext.current, useFakeData = true)

    HomeScreen(
        viewModel,
        navigateToArticle = {})
}
