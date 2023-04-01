package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavHostController

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
            /* TODO: remove search result screen (we don't need this any more
            navHostController.navigate(
                NavRoute.SearchResults.withArgs(
                    searchResultTitle,
                    searchQuery
                )
            )*/
            keyboardController!!.hide()
        },
    )
}