package vtsen.hashnode.dev.androidnews.ui.screens.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.ui.screens.common.AddIconButton
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun ArticleTopBar(navHostController: NavHostController, viewModel: MainViewModel) {
    TopAppBar {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            IconButton(onClick = { navHostController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }

            Row {

                val article = viewModel.currentArticle!!

                AddIconButton(
                    article = article,
                    onIconClick = viewModel::onReadClick,
                    iconPainter = if (article.read)
                        painterResource(R.drawable.ic_check_circle)
                    else
                        painterResource(R.drawable.ic_radio_button_unchecked)
                )

                AddIconButton(
                    article = article,
                    onIconClick = viewModel::onBookmarkClick,
                    iconPainter = if (article.bookmarked)
                        painterResource(R.drawable.ic_bookmarked)
                    else
                        painterResource(R.drawable.ic_bookmark_border),
                )

            }
        }
    }
}