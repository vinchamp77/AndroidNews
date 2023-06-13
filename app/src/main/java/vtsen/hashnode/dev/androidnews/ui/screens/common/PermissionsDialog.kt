package vtsen.hashnode.dev.androidnews.ui.screens.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionDialog(
    permission: String,
) {
    val permissionsState = rememberPermissionState(permission)
    var showRationale by remember { mutableStateOf(true) }
    var launchPermissionDialog by remember { mutableStateOf(true) }

    if (permissionsState.status.shouldShowRationale) {
        if(showRationale) {
            RationalPermissionDialog(
                permission,
                dismissCallback = {showRationale = false}
            )
        }

    } else if (!permissionsState.status.isGranted)  {
        if (launchPermissionDialog) {
            LaunchPermissionDialog(
                permission,
                permissionsState,
                dismissCallback = { launchPermissionDialog = false}
            )

            SideEffect {
                permissionsState.launchPermissionRequest()
            }
        }
    }
}