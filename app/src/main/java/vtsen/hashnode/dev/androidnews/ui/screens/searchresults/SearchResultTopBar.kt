package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.data.remote.WebService
import vtsen.hashnode.dev.androidnews.data.repository.SqlArticlesRepository
import vtsen.hashnode.dev.androidnews.di.DatabaseModule
import vtsen.hashnode.dev.androidnews.ui.viewmodel.MainViewModel

@Composable
fun SearchResultsTopBar(navHostController: NavHostController, viewModel: MainViewModel) {

    TopAppBar {

        val title = LocalContext.current.getString(
            R.string.searched_result_title,
            LocalContext.current.getString(viewModel.searchedResultResId!!),
        )

        Text(title)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val repository = SqlArticlesRepository(
        DatabaseModule.provideDatabase(LocalContext.current),
        WebService(),
    )
    val viewModel = MainViewModel(repository, useFakeData = true)
    val navHostController = rememberNavController()

    SearchResultsTopBar(
        navHostController,
        viewModel,
    )
}