package vtsen.hashnode.dev.androidnews.ui.screens.main.topbar

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.R

@Composable
fun SearchResultsTopBar(titleResId: Int) {

    TopAppBar {
        val title = LocalContext.current.getString(
            R.string.searched_result_title,
            LocalContext.current.getString(titleResId),
        )
        Text(title)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    SearchResultsTopBar(
        R.string.all_articles,
    )
}