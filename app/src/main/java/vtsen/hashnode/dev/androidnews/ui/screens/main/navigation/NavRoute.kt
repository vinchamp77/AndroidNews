package vtsen.hashnode.dev.androidnews.ui.screens.main.navigation

sealed class NavRoute(val path: String) {

    object Home: NavRoute("home")

    object Unread: NavRoute("unread")

    object Bookmarks: NavRoute("bookmarks")

    object Article: NavRoute("article") {
        val id = "id"
    }

    object SearchResults: NavRoute("search_results") {
        val titleResId = "title"
        val query = "query"
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


