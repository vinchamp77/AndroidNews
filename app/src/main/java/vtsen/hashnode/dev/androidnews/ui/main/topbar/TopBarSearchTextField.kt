package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var searchQuery by rememberSaveable { mutableStateOf("") }

    SearchTextField(
        modifier = modifier,
        searchQuery = searchQuery,
        onValueChange = { value ->
            searchQuery = value
        },
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