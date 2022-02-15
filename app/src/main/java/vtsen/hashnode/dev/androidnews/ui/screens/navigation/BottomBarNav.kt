package vtsen.hashnode.dev.androidnews.ui.screens.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import vtsen.hashnode.dev.androidnews.R

@Composable
fun BottomBarNav(navHostController: NavController) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation {

        val homeSelected = currentRoute == NavRoute.Home.path
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_home),
                    contentDescription = NavRoute.Home.path
                )
            },
            selected = homeSelected,
            onClick = {
                if(!homeSelected) {
                    navHostController.navigate(NavRoute.Home.path) {
                        popUpTo(NavRoute.Home.path) { inclusive = true }
                    }
                }
            },
            label = {Text(NavRoute.Home.path)}
        )

        val bookmarksSelected =  currentRoute == NavRoute.Bookmarks.path
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_bookmarks),
                    contentDescription = NavRoute.Bookmarks.path
                )
            },
            selected = bookmarksSelected,
            onClick = {
                if(!bookmarksSelected) {
                    navHostController.navigate(NavRoute.Bookmarks.path) {
                        popUpTo(NavRoute.Home.path) { inclusive = false }
                    }
                }
            },
            label = { Text(NavRoute.Bookmarks.path) }
        )
    }
}