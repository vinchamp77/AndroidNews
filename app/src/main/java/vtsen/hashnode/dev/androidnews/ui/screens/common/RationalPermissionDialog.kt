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
fun RationalPermissionDialog (
    permission: String,
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
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
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
        }
    )
}