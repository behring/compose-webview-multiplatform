package com.multiplatform.webview.response

import com.multiplatform.webview.web.WebViewNavigator

/**
 * Interface for receiving response to a specific resource in WebView.
 */
interface ReceiveResourceResponse {
    fun onResourceResponse(
        response: WebResponse,
        navigator: WebViewNavigator,
    )
}
