package vtsen.hashnode.dev.androidnews.ui.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingSmall

@Composable
fun NoArticlesScreen(titleStrResId: Int, descStrResId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(titleStrResId),
            style = MaterialTheme.typography.h4,
        )

        Spacer(Modifier.padding(PaddingSmall))
        Text(
            text = stringResource(descStrResId),
        )
    }
}
