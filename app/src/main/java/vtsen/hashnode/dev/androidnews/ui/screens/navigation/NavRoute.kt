package vtsen.hashnode.dev.androidnews.ui.screens.navigation

sealed class NavRoute(val path: String) {

    object Main: NavRoute("main")

    object Article: NavRoute("article") {
        val id = "id"
    }

    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    fun withArgsFormat(vararg args: String) : String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/{$arg}")
            }
        }
    }
}


