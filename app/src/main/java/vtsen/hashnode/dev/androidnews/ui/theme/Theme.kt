package vtsen.hashnode.dev.androidnews.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Color(0xff616161),
    primaryVariant = Color(0xff373737),
)

private val LightColorPalette = lightColors(
     primary = Color(0xff616161),
     primaryVariant = Color(0xff373737),

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AndroidNewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    useSystemUIController: Boolean,
    content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    if (useSystemUIController) {
        val systemUiController = rememberSystemUiController()
        systemUiController.setSystemBarsColor(
            color = colors.primaryVariant
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}