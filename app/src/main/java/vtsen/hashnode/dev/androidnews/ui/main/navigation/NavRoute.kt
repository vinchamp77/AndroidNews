package vtsen.hashnode.dev.androidnews.ui.main.navigation

sealed class NavRoute(val path: String) {

    object Home: NavRoute("home")

    object Unread: NavRoute("unread")

    object Bookmarks: NavRoute("bookmarks")

    object Article: NavRoute("article") {
        val id = "id"
    }

    object About: NavRoute("about")

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


