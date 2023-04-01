package vtsen.hashnode.dev.androidnews.ui.screens.common

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import vtsen.hashnode.dev.androidnews.domain.utils.ArticleUiUtils
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingSmall

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticlesScreen(
    articleUis: List<ArticleUi>,
    noArticlesDescStrResId: Int,
    isRefreshing: Boolean,
    isSearching: Boolean,
    navigateToArticle: (ArticleUi) -> Unit,
    onRefresh: () -> Unit,
    onBookmarkClick: (ArticleUi) -> Unit,
    onReadClick: (ArticleUi) -> Unit,
) {
    if (articleUis.isEmpty()) {
        NoArticlesScreen(noArticlesDescStrResId)
        return
    }

    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    Box(Modifier.pullRefresh(pullRefreshState)) {

        val context = LocalContext.current
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            items(items = articleUis) { article ->
                ArticleCard(
                    articleUi = article,
                    onArticleCardClick = navigateToArticle,
                    onBookmarkClick = onBookmarkClick,
                    onShareClick = { _article ->
                        shareArticle(context, _article.link)
                    },
                    onReadClick = onReadClick,
                )
            }
        }

        PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }

    if(isSearching) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
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
private fun ArticlesScreenPreview() {

    ArticlesScreen(
        articleUis = ArticleUiUtils.makeFakeArticles() ,
        noArticlesDescStrResId = R.string.no_articles_desc,
        isRefreshing = false,
        isSearching = false,
        navigateToArticle = {},
        onRefresh = {},
        onBookmarkClick = {},
        onReadClick = {},
    )
}
