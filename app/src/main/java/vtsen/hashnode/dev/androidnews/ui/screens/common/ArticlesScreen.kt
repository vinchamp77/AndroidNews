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
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.data.remote.WebService
import vtsen.hashnode.dev.androidnews.data.repository.SqlArticlesRepository
import vtsen.hashnode.dev.androidnews.ui.screens.home.ArticleCard
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingSmall
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModel

@Composable
fun ArticlesScreen(
    viewModel: MainViewModel,
    articles: List<Article>,
    navigateToArticle: (Int) -> Unit,
    noArticlesDescStrResId: Int,
) {

    if (articles.isEmpty()) {
        ShowNoArticles(noArticlesDescStrResId)
        return
    }

    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {


        items(items = articles) { article ->
            ArticleCard(
                article = article,
                onArticleCardClick = navigateToArticle,
                onBookmarkClick = viewModel::onBookmarkClick,
                onShareClick = {
                    shareArticle(context, article.link)
                },
                onReadClick = viewModel::onReadClick
            )
        }
    }
}

@Composable
private fun ShowNoArticles(noArticlesDescStrResId: Int) {
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

    val repository = SqlArticlesRepository(
        ArticlesDatabase.getInstance(LocalContext.current),
        WebService(),
    )
    val viewModel = MainViewModel(repository, useFakeData = true)

    ArticlesScreen(
        viewModel = viewModel,
        articles = viewModel.allArticles!!,
        navigateToArticle = {},
        noArticlesDescStrResId = R.string.no_articles_desc,
    )
}
