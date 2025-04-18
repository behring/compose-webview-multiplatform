package com.multiplatform.webview.response

/**
 * Created By Kevin Zou On 2023/11/29
 */
data class WebResponse(
    val url: String,
    val headers: MutableMap<String, String> = mutableMapOf(),
    val isForMainFrame: Boolean = false,
    val method: String = "GET",
)
