package vtsen.hashnode.dev.androidnews.ui.screens

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun ArticleScreen(viewModel: MainViewModel, id: String) {
    val article = viewModel.getArticle(id)
    LoadWebUrl(url = article.link)
}

@Composable
private fun LoadWebUrl(url: String) {

    if (url.isEmpty()) {
        return
    }

    Column {

        AndroidView(factory = {
            WebView(it).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        })
    }
}

@Composable
private fun LoadWebHtml(html: String) {

    if (html.isEmpty()) {
        return
    }

    Column {

        AndroidView(factory = {
            WebView(it).apply {
                webChromeClient = WebChromeClient()
                loadData(html, "text/html", "utf-8")
            }
        })
    }
}