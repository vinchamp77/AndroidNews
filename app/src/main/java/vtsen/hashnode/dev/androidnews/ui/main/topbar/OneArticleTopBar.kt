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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.repository.ArticlesRepositoryImpl
import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepositoryImpl
import vtsen.hashnode.dev.androidnews.domain.usecase.*
import vtsen.hashnode.dev.androidnews.ui.screens.article.OneArticleViewModel
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticleIconButton

@Composable
fun OneArticleTopBar(
    navHostController: NavHostController,
    viewModel: OneArticleViewModel,
) {

    val article by viewModel.article.collectAsStateWithLifecycle(null)

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

                article?.run {
                    ArticleIconButton(
                        articleUi = article!!,
                        onIconClick = viewModel::onReadClick,
                        iconPainter = if (article!!.read)
                            painterResource(R.drawable.ic_check_circle)
                        else
                            painterResource(R.drawable.ic_radio_button_unchecked)
                    )

                    ArticleIconButton(
                        articleUi = article!!,
                        onIconClick = viewModel::onBookmarkClick,
                        iconPainter = if (article!!.bookmarked)
                            painterResource(R.drawable.ic_bookmarked)
                        else
                            painterResource(R.drawable.ic_bookmark_border),
                    )
                }

                TopBarDropDownMenu(navHostController = navHostController)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val articlesRepository = ArticlesRepositoryImpl.getInstance(LocalContext.current)
    val userPrefsRepository = UserPreferencesRepositoryImpl.getInstance(LocalContext.current)
    val viewModel = OneArticleViewModel(
        GetArticleStatusUseCase(articlesRepository),
        RefreshArticlesStatusUseCase(articlesRepository),
        ClearArticlesStatusUseCase(articlesRepository),
        AddBookmarkArticlesUseCase(userPrefsRepository),
        RemoveBookmarkArticlesUseCase(userPrefsRepository),
        AddReadArticlesUseCase(userPrefsRepository),
        RemoveReadArticlesUseCase(userPrefsRepository),
        GetArticleUseCase(articlesRepository),
        articleId = "",
    )

    val navHostController = rememberNavController()

    OneArticleTopBar(
        navHostController,
        viewModel
    )
}
