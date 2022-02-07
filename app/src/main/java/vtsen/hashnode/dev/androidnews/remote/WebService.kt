package vtsen.hashnode.dev.androidnews.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class WebService {

    suspend fun getXMlString(url: String): String {
        val client = HttpClient()
        val response: HttpResponse = client.request(url)
        client.close()
        return response.body()
    }
}