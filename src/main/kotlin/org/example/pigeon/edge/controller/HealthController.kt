package org.example.pigeon.edge.controller

import com.google.inject.Inject
import com.google.inject.Singleton
import io.javalin.http.Context
import org.example.pigeon.core.server.BaseController
import org.example.pigeon.core.server.httpMethod.Get
import org.example.pigeon.edge.service.HealthService

@Singleton
class HealthController @Inject constructor(private val healthService: HealthService): BaseController {

    @Get("/health")
    fun getAppInfo(context: Context) {
        context.json(healthService.getAppInfo())
    }

}