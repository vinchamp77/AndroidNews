package vtsen.hashnode.dev.androidnews.ui.screens.common

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Job
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.screens.home.ArticleCard
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingSmall
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModel
import kotlin.reflect.KFunction1

@Composable
fun ArticlesScreen(
    articles: List<Article>,
    noArticlesDescStrResId: Int,
    isRefreshing: Boolean,
    navigateToArticle: (Article) -> Unit,
    onRefresh: () -> Unit,
    onBookmarkClick: (Article) -> Unit,
    onReadClick: (Article) -> Unit,
) {
    if (articles.isEmpty()) {
        NoArticlesScreen(noArticlesDescStrResId)
        return
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { onRefresh() }
    ) {
        val context = LocalContext.current
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {

            items(items = articles) { article ->
                ArticleCard(
                    article = article,
                    onArticleCardClick = navigateToArticle,
                    onBookmarkClick = onBookmarkClick,
                    onShareClick = { article ->
                        shareArticle(context, article.link)
                    },
                    onReadClick = onReadClick,
                )
            }
        }
    }
}

@Composable
private fun NoArticlesScreen(noArticlesDescStrResId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_articles),
            style = MaterialTheme.typography.h4,
        )

        Spacer(Modifier.padding(PaddingSmall))
        Text(
            text = stringResource(noArticlesDescStrResId),
        )
    }
}

private fun shareArticle(context: Context, link: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, link)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    ContextCompat.startActivity(context, shareIntent, null)
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current)
    val viewModel = MainViewModel(repository, useFakeData = true)

    ArticlesScreen(
        articles = viewModel.allArticles!! ,
        noArticlesDescStrResId = R.string.no_articles_desc,
        isRefreshing = viewModel.isRefreshing,
        navigateToArticle = {},
        onRefresh = viewModel::refresh,
        onBookmarkClick = viewModel::onBookmarkClick,
        onReadClick = viewModel::onReadClick,
    )
}
