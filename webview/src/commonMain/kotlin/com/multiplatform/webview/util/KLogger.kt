package com.multiplatform.webview.util

import co.touchlab.kermit.DefaultFormatter
import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.kermit.mutableLoggerConfigInit
import co.touchlab.kermit.platformLogWriter

/**
 * Created By Kevin Zou On 2023/10/16
 */
internal object KLogger : Logger(
    config = mutableLoggerConfigInit(listOf(platformLogWriter(DefaultFormatter))),
    tag = "ComposeWebView",
) {
    init {
        setMinSeverity(KLogSeverity.Debug)
    }

    fun setMinSeverity(severity: KLogSeverity) {
        mutableConfig.minSeverity = severity.toKermitSeverity()
    }

    // For iOS, it will not print out the log if the severity is upper than Debug in AS.
    fun info(msg: () -> String) {
        d { msg() }
    }
}

enum class KLogSeverity {
    Verbose,
    Debug,
    Info,
    Warn,
    Error,
    Assert,
}

fun KLogSeverity.toKermitSeverity(): Severity {
    return when (this) {
        KLogSeverity.Verbose -> Severity.Verbose
        KLogSeverity.Debug -> Severity.Debug
        KLogSeverity.Info -> Severity.Info
        KLogSeverity.Warn -> Severity.Warn
        KLogSeverity.Error -> Severity.Error
        KLogSeverity.Assert -> Severity.Assert
    }
}
