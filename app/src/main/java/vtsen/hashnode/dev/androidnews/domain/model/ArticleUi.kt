package vtsen.hashnode.dev.androidnews.domain.model

data class ArticleUi(
    val id: String,
    val title: String,
    val link: String,
    val author: String,
    val pubDate: Long,
    val image: String,
    val feedTitle: String,
    val bookmarked: Boolean,
    val read: Boolean,
)