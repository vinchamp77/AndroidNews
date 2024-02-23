/*
 * Copyright 2023 Vincent Tsen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vtsen.hashnode.dev.androidnews.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse

class KtorClientWebService : WebService {
    override suspend fun getXMlString(url: String): String {
        val client = HttpClient()
        /*
        This throws the following exception when it run on API 21:
        java.util.concurrent.ExecutionException: java.lang.NoClassDefFoundError: io.ktor.util.collections.ConcurrentMap$$ExternalSyntheticLambda0

        Use OkHttpWebService instead
         */
        val response: HttpResponse = client.request(url)
        client.close()
        return response.body()
    }
}
