package vtsen.hashnode.dev.androidnews.ui.main

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource

@Composable
fun SnackBar(scaffoldState: ScaffoldState, msgResId: Int) {
    val msg = stringResource(msgResId)
    LaunchedEffect(msg) {
        scaffoldState.snackbarHostState.showSnackbar(
            message = msg,
        )
    }
}

