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

import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import vtsen.hashnode.dev.androidnews.ui.main.navigation.NavRoute

@Composable
fun TopBarDropDownMenu(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    var expandedDropDownMenu by remember { mutableStateOf(false) }

    Box(
        modifier = modifier,
    ) {
        IconButton(onClick = {
            expandedDropDownMenu = true
        }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
            )
        }

        DropdownMenu(
            expanded = expandedDropDownMenu,
            onDismissRequest = {
                expandedDropDownMenu = false
            },
        ) {
            DropdownMenuItem(
                onClick = {
                    expandedDropDownMenu = false
                    navHostController.navigate(NavRoute.About.path)
                },
            ) {
                Text(text = "About")
            }
        }
    }
}
