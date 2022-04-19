package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.data.local.ArticlesDatabase
import vtsen.hashnode.dev.androidnews.data.remote.WebService
import vtsen.hashnode.dev.androidnews.data.repository.MainRepository
import vtsen.hashnode.dev.androidnews.ui.screens.navigation.NavRoute
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingSmall
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ArticlesTopBar(
    navHostController: NavHostController,
    viewModel: MainViewModel,
    onArticlesSearch: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    viewModel.clearSearchQuery()

    TopAppBar(
        contentPadding = PaddingValues(PaddingSmall)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20))
                .background(color = MaterialTheme.colors.background)

            ,
            value = viewModel.searchQuery,
            onValueChange = viewModel::onSearchQueryChanged,

            label = { Text(text = "Search") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onArticlesSearch()
                    navHostController.navigate(NavRoute.SearchResults.path)
                    keyboardController!!.hide()
                },
            ),
            singleLine = true,
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface)
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val repository = MainRepository(
        ArticlesDatabase.getInstance(LocalContext.current),
        WebService(),
    )
    val viewModel = MainViewModel(repository, useFakeData = true)
    val navHostController = rememberNavController()

    ArticlesTopBar(
        navHostController,
        viewModel,
        viewModel::onAllArticlesSearch
    )
}