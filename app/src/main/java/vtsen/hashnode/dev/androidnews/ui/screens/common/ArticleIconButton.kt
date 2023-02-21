package vtsen.hashnode.dev.androidnews.ui.screens.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi

@Composable
fun ArticleIconButton(
    articleUi: ArticleUi,
    onIconClick: (ArticleUi) -> Unit,
    iconPainter: Painter,
    contentDescription: String? = null,
) {
    IconButton(onClick = { onIconClick(articleUi) }) {
        Icon(
            painter = iconPainter,
            contentDescription = contentDescription
        )
    }
}