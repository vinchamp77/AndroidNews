package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onValueChange: (String) -> Unit,
    onSearch: KeyboardActionScope.() -> Unit,
) {
    TextField(
        modifier = modifier
            .clip(RoundedCornerShape(20))
            .background(color = MaterialTheme.colors.background),
        value = searchQuery,
        onValueChange = onValueChange,

        label = { Text(text = "Search") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "") },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = onSearch,
        ),
        singleLine = true,
        textStyle = TextStyle(color = MaterialTheme.colors.onSurface)
    )
}