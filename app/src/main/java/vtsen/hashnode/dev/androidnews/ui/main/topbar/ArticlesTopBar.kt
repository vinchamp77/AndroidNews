/*
 * Copyright 2023 Vincent Tsen
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
package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingSmall

@Composable
fun ArticlesTopBar(
    navHostController: NavHostController,
    searchQuery: String,
    onSearchQuery: (String) -> Unit,
) {
    TopAppBar(
        contentPadding = PaddingValues(PaddingSmall),
    ) {
        TopBarSearchTextField(
            modifier = Modifier.weight(0.9f),
            searchQuery = searchQuery,
            onSearchQuery = onSearchQuery,
        )

        TopBarDropDownMenu(
            modifier = Modifier.weight(0.1f),
            navHostController = navHostController,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    val navHostController = rememberNavController()

    ArticlesTopBar(navHostController, searchQuery = "", onSearchQuery = {})
}
