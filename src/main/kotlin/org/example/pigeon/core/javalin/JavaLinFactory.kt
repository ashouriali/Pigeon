package org.example.pigeon.core.javalin

import com.google.inject.AbstractModule
import com.google.inject.Provides
import org.example.pigeon.core.guice.Factory
import org.example.pigeon.core.utils.jackson
import io.javalin.Javalin
import io.javalin.json.JavalinJackson
import jakarta.inject.Singleton

@Factory
class JavaLinFactory: AbstractModule() {

    private val app: Javalin = Javalin.create {
        it.jsonMapper(JavalinJackson(jackson))
    }

    @Singleton
    @Provides
    fun createApp(): Javalin {
        return app
    }
}