package com.example.bkmtechnologysolutions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bkmtechnologysolutions.ui.theme.BKMTechnologySolutionsTheme
import android.webkit.WebView
import androidx.compose.ui.viewinterop.AndroidView
import android.webkit.WebViewClient
import android.webkit.WebResourceRequest
import android.content.Intent
import android.net.Uri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BKMTechnologySolutionsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WebPage(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun WebPage(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                loadUrl("https://www.bkm-tech.com")
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                        val url = request?.url.toString() ?: return false
                        return if (url.contains("bkm-tech.com")) {
                            false  // Load in WebView
                        } else {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            view?.context?.startActivity(intent)
                            true  // Override to open externally
                        }
                    }
                }
            }
        },
        modifier = modifier.fillMaxSize()
    )
}
