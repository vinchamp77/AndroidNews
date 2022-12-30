package vtsen.hashnode.dev.androidnews.ui.screens.article

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ArticleScreen(viewModel: OneArticleViewModel) {

    val article by viewModel.article.collectAsStateWithLifecycle()

    if (article != null) {
        ArticleWebView(url = article!!.link)
    }
}

@Composable
private fun ArticleWebView(url: String) {

    if (url.isEmpty()) {
        return
    }

    Column {

        AndroidView(factory = {
            WebView(it).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        })
    }
}

//For offline-mode reference if it needs to be supported in future
//it does not 100 % working correctly
@Composable
private fun ArticleWebView(title: String, html: String) {

    if (html.isEmpty()) {
        return
    }

    Column {

        Text(text = title, style = MaterialTheme.typography.h4)

        AndroidView(factory = {
            WebView(it).apply {
                webChromeClient = WebChromeClient()
                loadData(html, "text/html", "utf-8")
            }
        })
    }
}