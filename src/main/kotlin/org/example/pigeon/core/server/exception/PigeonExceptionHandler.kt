package org.example.pigeon.core.server.exception

import io.javalin.http.Context
import io.javalin.http.ExceptionHandler
import org.example.pigeon.core.exception.PigeonBaseException
import org.example.pigeon.core.log.PigeonServiceLogger

class PigeonExceptionHandler: ExceptionHandler<Exception> {
    private val logger = PigeonServiceLogger { PigeonExceptionHandler::class.simpleName }
    override fun handle(exception: Exception, ctx: Context) {
        logger.error(exception) { "Error occurred in processing request: ${ctx.req().requestURI}" }
        if (exception is PigeonBaseException) {
            ctx.json(
                PigeonError(
                    exception.message ?: "",
                    exception.statusCode,
                    "",
                    "",
                )
            )
            ctx.status(exception.statusCode)
        } else {
            ctx.json(
                PigeonError(
                    exception.message ?: "",
                    500,
                    "INTERNAL_SERVER_ERROR",
                    ""
                )
            )
            ctx.status(500)
        }
    }

}