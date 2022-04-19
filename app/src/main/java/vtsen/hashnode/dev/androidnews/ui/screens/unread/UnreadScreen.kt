package vtsen.hashnode.dev.androidnews.ui.screens.unread

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.data.remote.WebService
import vtsen.hashnode.dev.androidnews.data.repository.MainRepository
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticlesScreen
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModel

@Composable
fun UnreadScreen(
    viewModel: MainViewModel,
    navigateToArticle: (Int) -> Unit,
) {
    if(viewModel.unreadArticles == null) return

    ArticlesScreen(
        viewModel = viewModel,
        articles = viewModel.unreadArticles!! ,
        navigateToArticle = navigateToArticle,
        noArticlesDescStrResId = R.string.no_unread_articles_desc
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val repository = MainRepository(
        ArticlesDatabase.getInstance(LocalContext.current),
        WebService(),
    )
    val viewModel = MainViewModel(repository, useFakeData = true)

    UnreadScreen(
        viewModel,
        navigateToArticle = {})
}
