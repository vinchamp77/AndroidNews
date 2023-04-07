package vtsen.hashnode.dev.androidnews.ui.main.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import vtsen.hashnode.dev.androidnews.ui.main.navigation.NavRoute

@Composable
fun TopBarDropDownMenu(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    showReviewDialog: () -> Unit,
){
    var expandedDropDownMenu by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {

        IconButton(onClick = {
            expandedDropDownMenu = true
        }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expandedDropDownMenu,
            onDismissRequest = {
                expandedDropDownMenu = false
            }
        ) {
            DropdownMenuItem(
                onClick = {
                    expandedDropDownMenu = false
                    navHostController.navigate(NavRoute.About.path)
                }
            ) {
                Text(text = "About")
            }

            DropdownMenuItem(
                onClick = {
                    expandedDropDownMenu = false
                    showReviewDialog()
                }
            ) {
                Text(text = "Rate Me")
            }
        }
    }
}