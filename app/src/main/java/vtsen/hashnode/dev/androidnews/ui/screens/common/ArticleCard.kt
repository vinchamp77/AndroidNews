package vtsen.hashnode.dev.androidnews.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingMedium
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingSmall
import vtsen.hashnode.dev.androidnews.utils.Utils

@Composable
fun ArticleCard(
    article: Article,
    onArticleCardClick: (Article) -> Unit,
    onBookmarkClick: (Article) -> Unit,
    onShareClick: (Article) -> Unit,
    onReadClick: (Article) -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingMedium)
    ) {
        ArticleRow(article, onArticleCardClick)
        Spacer(Modifier.padding(PaddingSmall))

        ArticleBottomRow(article, onBookmarkClick, onShareClick, onReadClick)
        Spacer(Modifier.padding(PaddingSmall))

        Divider(thickness = 2.dp)
    }
}

@Composable
private fun ArticleRow(
    article: Article,
    onArticleCardClick: (Article) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onArticleCardClick(article)
            }
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ArticleContent(article)
        ArticleImage(article)
    }
}

@Composable
private fun ArticleContent(article: Article) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(end = PaddingSmall)
    ) {

        Text(text = article.title, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.padding(PaddingSmall))

        Text(text = article.feedTitle)
        Text(text = article.author)

        Spacer(Modifier.padding(PaddingMedium))
        Text(text = Utils.parseDateLongToElapsedTime(article.pubDate))
    }

}

@Composable
private fun ArticleImage(article: Article) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(article.image)
            .placeholder(R.drawable.loading_animation)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = Modifier
            .size(150.dp, 150.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
private fun ArticleBottomRow(
    article: Article,
    onBookmarkClick: (Article) -> Unit,
    onShareClick: (Article) -> Unit,
    onReadClick: (Article) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
        ,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ArticleIconButton(
            article = article,
            onIconClick = onBookmarkClick,
            iconPainter = if (article.bookmarked)
                painterResource(R.drawable.ic_bookmarked)
            else
                painterResource(R.drawable.ic_bookmark_border),
        )

        ArticleIconButton(
            article = article,
            onIconClick = onShareClick,
            iconPainter = painterResource(R.drawable.ic_share),
        )

        ArticleIconButton(
            article = article,
            onIconClick = onReadClick,
            iconPainter = if (article.read)
                painterResource(R.drawable.ic_check_circle)
            else
                painterResource(R.drawable.ic_radio_button_unchecked)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleCardPreview() {
    ArticleCard(
        article = Utils.createArticle(),
        onArticleCardClick = {},
        onBookmarkClick = {},
        onShareClick = {},
        onReadClick = {},
    )
}


