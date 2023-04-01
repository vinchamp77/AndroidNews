package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopBarSearchTextField(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQuery: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    SearchTextField(
        modifier = modifier,
        searchQuery = searchQuery,
        onValueChange = onSearchQuery,
        onSearch = {
            keyboardController!!.hide()
        },
    )
}