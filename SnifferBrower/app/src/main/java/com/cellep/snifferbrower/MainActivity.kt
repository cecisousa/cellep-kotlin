package com.cellep.snifferbrower

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val WEBURL = "https://google.com"
    private val FONTE_SEARCH = "/search?q="
    private val dog = "http://imageshack.com/a/img923/4004/1U0qAe.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgVoltar.setOnClickListener {
            if(webView.canGoBack()) {
                webView.goBack()
            } else {
                Toast.makeText(this, "Não existe histórico de navegação", Toast.LENGTH_LONG).show()
            }
        }

        imgIr.setOnClickListener {
            if (webView.canGoForward()){
                webView.goForward()
            } else {
                Toast.makeText(this, "Não existe histórico de navegação", Toast.LENGTH_LONG).show()
            }
        }

        imgAtualizar.setOnClickListener {
            webView.reload()
        }

        swipeRefresh.setOnRefreshListener {
            webView.reload()
        }

        srcView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {

                p0?.let {
                    if(URLUtil.isValidUrl(it)) {
                        // url
                        webView.loadUrl(it)
                    } else {
                        webView.loadUrl("$WEBURL$FONTE_SEARCH$it")
                    }
                }
                return false
            }
        })

        webView.webChromeClient = object : WebChromeClient() {

        }

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                srcView.setQuery(url, false)

                swipeRefresh.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                swipeRefresh.isRefreshing = false
            }
        }

        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.loadUrl(dog)

    }

    override fun onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}

