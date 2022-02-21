package vtsen.hashnode.dev.androidnews.viewmodel

data class Article(
    val id: Int,
    val title: String,
    val description: String,
    val link: String,
    val pubDate: Long,
    val image: String,
    val bookmarked: Boolean,
)