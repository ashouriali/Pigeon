package org.example.pigeon.core.server.logging

import io.javalin.http.Context
import io.javalin.http.RequestLogger
import org.example.pigeon.core.log.PigeonServiceLogger

class PigeonRequestLogger: RequestLogger {
    private val logger = PigeonServiceLogger()
    override fun handle(ctx: Context, executionTimeMs: Float) {
        logger.info { "Request with url: ${ctx.fullUrl()} executed in $executionTimeMs ms." }
    }
}