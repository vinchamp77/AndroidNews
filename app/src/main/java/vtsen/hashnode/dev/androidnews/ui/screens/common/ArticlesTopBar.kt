package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.ui.screens.navigation.NavRoute
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ArticlesTopBar(
    navHostController: NavHostController,
    viewModel: MainViewModel,
    onArticlesSearch: () -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.searchQuery,
                onValueChange = viewModel::onSearchQueryChanged,

                label = { Text(text = "Search") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onArticlesSearch()
                        navHostController.navigate(NavRoute.SearchResults.path)
                        keyboardController!!.hide()
                    },
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val viewModel = MainViewModel(LocalContext.current, preview = true)
    val navHostController = rememberNavController()

    ArticlesTopBar(
        navHostController,
        viewModel,
        viewModel::onAllArticlesSearch
    )
}