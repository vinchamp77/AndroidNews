package vtsen.hashnode.dev.androidnews.ui.screens.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import vtsen.hashnode.dev.androidnews.R

@Composable
fun BottomBarNav(navHostController: NavController) {

    BottomNavigation {
        // Home
        AddBottomNavigationItem(
            rowScope = this,
            navHostController = navHostController,
            drawableId = R.drawable.ic_home,
            targetNavRoutePath = NavRoute.Home.path,
            labelStringResourceId = R.string.home,
        )
        // Unread
        AddBottomNavigationItem(
            rowScope = this,
            navHostController = navHostController,
            drawableId = R.drawable.ic_article,
            targetNavRoutePath = NavRoute.Unread.path,
            labelStringResourceId = R.string.unread_articles,
        )
        // Bookmarks
        AddBottomNavigationItem(
            rowScope = this,
            navHostController = navHostController,
            drawableId = R.drawable.ic_bookmarks,
            targetNavRoutePath = NavRoute.Bookmarks.path,
            labelStringResourceId = R.string.bookmarks,
        )
    }
}

@Composable
private fun AddBottomNavigationItem(
    rowScope: RowScope,
    navHostController: NavController,
    drawableId: Int,
    targetNavRoutePath: String,
    labelStringResourceId: Int) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentNavRoutePath = navBackStackEntry?.destination?.route

    val selected = currentNavRoutePath == targetNavRoutePath
    rowScope.BottomNavigationItem(
        icon = {
            Icon(
                painter = painterResource(drawableId),
                contentDescription = stringResource(labelStringResourceId)
            )
        },
        selected = selected,
        onClick = {
            if(!selected) {
                navHostController.navigate(targetNavRoutePath) {
                    popUpTo(NavRoute.Home.path) {
                        inclusive = (targetNavRoutePath == NavRoute.Home.path)
                    }
                }
            }
        },
        label = {Text(stringResource(labelStringResourceId))}
    )
}