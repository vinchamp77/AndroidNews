/*
 * Copyright 2025 Vincent Tsen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vtsen.hashnode.dev.androidnews.ui.main.navigation

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
        BottomNavigationItem(
            rowScope = this,
            navHostController = navHostController,
            drawableId = R.drawable.ic_home,
            targetNavRoutePath = NavRoute.Home.path,
            labelStringResourceId = R.string.home,
        )
        // Unread
        BottomNavigationItem(
            rowScope = this,
            navHostController = navHostController,
            drawableId = R.drawable.ic_article,
            targetNavRoutePath = NavRoute.Unread.path,
            labelStringResourceId = R.string.unread_articles,
        )
        // Bookmarks
        BottomNavigationItem(
            rowScope = this,
            navHostController = navHostController,
            drawableId = R.drawable.ic_bookmarks,
            targetNavRoutePath = NavRoute.Bookmarks.path,
            labelStringResourceId = R.string.bookmarks,
        )
    }
}

@Composable
private fun BottomNavigationItem(
    rowScope: RowScope,
    navHostController: NavController,
    drawableId: Int,
    targetNavRoutePath: String,
    labelStringResourceId: Int,
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentNavRoutePath = navBackStackEntry?.destination?.route

    val selected = currentNavRoutePath == targetNavRoutePath
    rowScope.BottomNavigationItem(
        icon = {
            Icon(
                painter = painterResource(drawableId),
                contentDescription = stringResource(labelStringResourceId),
            )
        },
        selected = selected,
        onClick = {
            if (!selected) {
                navHostController.navigate(targetNavRoutePath) {
                    popUpTo(NavRoute.Home.path) {
                        inclusive = (targetNavRoutePath == NavRoute.Home.path)
                    }
                }
            }
        },
        label = { Text(stringResource(labelStringResourceId)) },
    )
}
