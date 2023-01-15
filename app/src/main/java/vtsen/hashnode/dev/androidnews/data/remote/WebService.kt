package vtsen.hashnode.dev.androidnews.data.remote

interface WebService {
    suspend fun getXMlString(url: String): String
}