package vtsen.hashnode.dev.androidnews.domain.model

data class Article(
    val id: Int,
    val title: String,
    val link: String,
    val author: String,
    val pubDate: Long,
    val image: String,
    val bookmarked: Boolean,
    val read: Boolean,

    val feedTitle: String,
)