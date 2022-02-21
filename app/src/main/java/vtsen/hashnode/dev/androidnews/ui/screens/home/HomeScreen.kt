package vtsen.hashnode.dev.androidnews.ui.screens.home

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    navigateToArticle: (Int) -> Unit,
) {
    val articles = viewModel.articles ?: return
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

private fun shareArticle(context: Context, link: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, link)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(context, shareIntent, null)
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val viewModel = MainViewModel(LocalContext.current, preview = true)

    HomeScreen(
        viewModel,
        navigateToArticle = {})
}
