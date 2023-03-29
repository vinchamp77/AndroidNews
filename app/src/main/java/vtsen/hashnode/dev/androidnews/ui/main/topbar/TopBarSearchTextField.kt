package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavHostController
import vtsen.hashnode.dev.androidnews.ui.main.navigation.NavRoute

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopBarSearchTextField(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    searchResultTitle: String,
    searchQuery: String,
    onSearchQuery: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    SearchTextField(
        modifier = modifier,
        searchQuery = searchQuery,
        onValueChange = onSearchQuery,
        onSearch = {
            navHostController.navigate(
                NavRoute.SearchResults.withArgs(
                    searchResultTitle,
                    searchQuery
                )
            )
            keyboardController!!.hide()
        },
    )
}