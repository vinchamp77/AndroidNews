package vtsen.hashnode.dev.androidnews.ui.screens.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LaunchPermissionDialog (
    permission: String,
    permissionState: PermissionState,
    dismissCallback: () -> Unit
) {
    val context = LocalContext.current

    val permissionLabel = stringResource(
        context.packageManager.getPermissionInfo(permission, 0).labelRes
    )
    val dialogText = "\"$permissionLabel\" permission is required when new article arrives."

    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Permission Required!") },
        text = { Text(text = dialogText) },
        confirmButton = {
            Button(onClick = {
                permissionState.launchPermissionRequest()
            }) {
                Text(text = "Launch")
            }
        },
        dismissButton = {
            Button(onClick = {
                dismissCallback()
            }) {
                Text(text = "Cancel")
            }
        }
    )
}