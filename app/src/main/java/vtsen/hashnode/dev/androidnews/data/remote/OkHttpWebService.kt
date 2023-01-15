package vtsen.hashnode.dev.androidnews.data.remote

import okhttp3.OkHttpClient
import okhttp3.Request

class OkHttpWebService : WebService {

    override suspend fun getXMlString(url: String): String {
        val client = OkHttpClient()

        val request: Request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()

        return response.body?.string() ?: ""
    }
}