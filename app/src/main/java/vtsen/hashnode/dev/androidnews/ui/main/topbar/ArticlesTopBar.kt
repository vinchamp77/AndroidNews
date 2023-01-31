package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingSmall

@Composable
fun ArticlesTopBar(
    navHostController: NavHostController,
    searchResultTitleResId: Int,
) {
    TopAppBar(
        contentPadding = PaddingValues(PaddingSmall)
    ) {
        TopBarSearchTextField(
            modifier = Modifier.weight(0.9f),
            navHostController = navHostController,
            searchResultTitle = searchResultTitleResId.toString())

        TopBarDropDownMenu(
            modifier = Modifier.weight(0.1f),
            navHostController = navHostController,
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val navHostController = rememberNavController()

    ArticlesTopBar(navHostController, R.string.all_articles)
}