package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticleIconButton
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun OneArticleTopBar(
    navHostController: NavHostController,
    viewModel: ArticlesViewModel,
    articleId: Int) {

    val article by viewModel.getArticle(articleId).collectAsStateWithLifecycle(null)

    if (article == null) return

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

                article!!.run {
                    ArticleIconButton(
                        article = article!!,
                        onIconClick = viewModel::onReadClick,
                        iconPainter = if (article!!.read)
                            painterResource(R.drawable.ic_check_circle)
                        else
                            painterResource(R.drawable.ic_radio_button_unchecked)
                    )

                    ArticleIconButton(
                        article = article!!,
                        onIconClick = viewModel::onBookmarkClick,
                        iconPainter = if (article!!.bookmarked)
                            painterResource(R.drawable.ic_bookmarked)
                        else
                            painterResource(R.drawable.ic_bookmark_border),
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val repository = ArticlesRepositoryImpl.getInstance(LocalContext.current)
    val viewModel = ArticlesViewModel(repository)
    val navHostController = rememberNavController()

    OneArticleTopBar(
        navHostController,
        viewModel,
        0
    )
}
