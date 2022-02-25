package vtsen.hashnode.dev.androidnews.repository.remote

data class FeedItem(
    val title: String,
    val link: String,
    val author: String,
    val pubDate: String,
    val image: String,
)