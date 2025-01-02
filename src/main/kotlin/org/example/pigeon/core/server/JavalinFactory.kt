package org.example.pigeon.core.server

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import io.javalin.Javalin
import io.javalin.http.ContentType
import io.javalin.json.JavalinJackson
import org.example.pigeon.core.config.AppConfig
import org.example.pigeon.core.config.AppMode
import org.example.pigeon.core.guice.Factory
import org.example.pigeon.core.server.exception.PigeonExceptionHandler
import org.example.pigeon.core.server.logging.PigeonRequestLogger
import org.example.pigeon.core.utils.jackson
import java.util.concurrent.ThreadLocalRandom

@Factory
class JavalinFactory: AbstractModule() {

    @Singleton
    @Provides
    fun createJavalinHttpServer(config: AppConfig): Javalin {
        val port =
            if (config.appMode == AppMode.TEST)
                ThreadLocalRandom.current().nextInt(10000, 65000)
            else config.port

        return Javalin.create {
//            it.http.defaultContentType = ContentType.JSON
            it.http.maxRequestSize = 100_000_000L
            it.jetty.defaultPort = port
            it.jetty.defaultHost = config.host
            it.jsonMapper(JavalinJackson(jackson))
            it.requestLogger.http(PigeonRequestLogger())
        }.exception(
            Exception::class.java,
            PigeonExceptionHandler()
        )
    }

}