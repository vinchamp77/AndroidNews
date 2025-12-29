/*
 * Copyright 2025 Vincent Tsen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vtsen.hashnode.dev.androidnews.ui.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi
import vtsen.hashnode.dev.androidnews.domain.utils.ArticleUiUtils
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingMedium
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingSmall
import vtsen.hashnode.dev.androidnews.utils.Utils

@Composable
fun ArticleCard(
    articleUi: ArticleUi,
    onArticleCardClick: (ArticleUi) -> Unit,
    onBookmarkClick: (ArticleUi) -> Unit,
    onShareClick: (ArticleUi) -> Unit,
    onReadClick: (ArticleUi) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(PaddingMedium),
    ) {
        ArticleRow(articleUi, onArticleCardClick)
        Spacer(Modifier.padding(PaddingSmall))

        ArticleBottomRow(articleUi, onBookmarkClick, onShareClick, onReadClick)
        Spacer(Modifier.padding(PaddingSmall))

        Divider(thickness = 2.dp)
    }
}

@Composable
private fun ArticleRow(
    articleUi: ArticleUi,
    onArticleCardClick: (ArticleUi) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onArticleCardClick(articleUi)
                },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ArticleContent(articleUi)
        ArticleImage(articleUi)
    }
}

@Composable
private fun ArticleContent(articleUi: ArticleUi) {
    Column(
        modifier =
            Modifier
                .width(200.dp)
                .padding(end = PaddingSmall),
    ) {
        Text(text = articleUi.title, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.padding(PaddingSmall))

        Text(text = articleUi.feedTitle)
        Text(text = articleUi.author)

        Spacer(Modifier.padding(PaddingMedium))
        Text(text = Utils.parseDateLongToElapsedTime(articleUi.pubDate))
    }
}

@Composable
private fun ArticleImage(articleUi: ArticleUi) {
    AsyncImage(
        model =
            ImageRequest
                .Builder(LocalContext.current)
                .data(articleUi.image)
                .placeholder(R.drawable.loading_animation)
                .build(),
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier =
            Modifier
                .size(150.dp, 150.dp)
                .clip(MaterialTheme.shapes.medium),
    )
}

@Composable
private fun ArticleBottomRow(
    articleUi: ArticleUi,
    onBookmarkClick: (ArticleUi) -> Unit,
    onShareClick: (ArticleUi) -> Unit,
    onReadClick: (ArticleUi) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ArticleIconButton(
            articleUi = articleUi,
            onIconClick = onBookmarkClick,
            iconPainter =
                if (articleUi.bookmarked) {
                    painterResource(R.drawable.ic_bookmarked)
                } else {
                    painterResource(R.drawable.ic_bookmark_border)
                },
        )

        ArticleIconButton(
            articleUi = articleUi,
            onIconClick = onShareClick,
            iconPainter = painterResource(R.drawable.ic_share),
        )

        ArticleIconButton(
            articleUi = articleUi,
            onIconClick = onReadClick,
            iconPainter =
                if (articleUi.read) {
                    painterResource(R.drawable.ic_check_circle)
                } else {
                    painterResource(R.drawable.ic_radio_button_unchecked)
                },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleCardPreview() {
    ArticleCard(
        articleUi = ArticleUiUtils.createArticle(),
        onArticleCardClick = {},
        onBookmarkClick = {},
        onShareClick = {},
        onReadClick = {},
    )
}
