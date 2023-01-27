package vtsen.hashnode.dev.androidnews.ui.screens.article

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import vtsen.hashnode.dev.androidnews.ui.screens.common.UrlWebView

@Composable
fun ArticleScreen(viewModel: OneArticleViewModel) {

    val article by viewModel.article.collectAsStateWithLifecycle()

    if (article != null) {
        UrlWebView(url = article!!.link)
    }
}
