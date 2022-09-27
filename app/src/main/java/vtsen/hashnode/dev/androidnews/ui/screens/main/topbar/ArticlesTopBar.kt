package vtsen.hashnode.dev.androidnews.ui.screens.main.topbar

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.ui.screens.main.navigation.NavRoute
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingSmall

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ArticlesTopBar(
    navHostController: NavHostController,
    searchTitleResId: Int,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var searchQuery by rememberSaveable { mutableStateOf("") }

    TopAppBar(
        contentPadding = PaddingValues(PaddingSmall)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20))
                .background(color = MaterialTheme.colors.background)

            ,
            value = searchQuery,
            onValueChange = { value ->
                searchQuery = value
            },

            label = { Text(text = "Search") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    navHostController.navigate(NavRoute.SearchResults.withArgs(
                        searchTitleResId.toString(),
                        searchQuery))
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

    val navHostController = rememberNavController()

    ArticlesTopBar(navHostController, R.string.all_articles)
}