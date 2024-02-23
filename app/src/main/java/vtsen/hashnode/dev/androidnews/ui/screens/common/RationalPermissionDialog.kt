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
package vtsen.hashnode.dev.androidnews.ui.screens.common

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat

@Composable
fun RationalPermissionDialog(
    permission: String,
    dismissCallback: () -> Unit,
) {
    val context = LocalContext.current
    val permissionLabel =
        stringResource(
            context.packageManager.getPermissionInfo(permission, 0).labelRes,
        )
    val dialogText = "\"$permissionLabel\" permission is required when new article arrives."

    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Permission Required!") },
        text = { Text(text = dialogText) },
        confirmButton = {
            Button(onClick = {
                val intent =
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        .apply {
                            data = Uri.fromParts("package", context.packageName, null)
                        }
                ContextCompat.startActivity(context, intent, null)
            }) {
                Text(text = "Go to settings")
            }
        },
        dismissButton = {
            Button(onClick = {
                dismissCallback()
            }) {
                Text(text = "Cancel")
            }
        },
    )
}
