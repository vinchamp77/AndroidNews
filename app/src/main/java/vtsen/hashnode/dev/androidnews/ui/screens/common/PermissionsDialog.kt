package vtsen.hashnode.dev.androidnews.ui.screens.common

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

@Composable
fun PermissionsDialog(
    permission: String,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
) {
    val activityResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->

        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }

    SideEffect {
        activityResultLauncher.launch(permission)
    }
}