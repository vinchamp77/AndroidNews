package vtsen.hashnode.dev.androidnews.data.mapper

import java.net.URI

fun String.toUrlPath(): String {
    val url = URI(this)
    val path = url.path.removePrefix("/")
    return path
}
