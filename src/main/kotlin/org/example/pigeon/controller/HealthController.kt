package org.example.pigeon.controller

import io.javalin.Javalin
import io.javalin.http.Context
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.example.pigeon.core.controller.BaseController
import org.example.pigeon.core.service.HealthService

@Singleton
class HealthController @Inject constructor(
    private val healthService: HealthService,
    private val app: Javalin,
) : BaseController {

    companion object {
        const val HEALTH = "/health"
    }

    override fun handleRequests() {
        app.get(HEALTH) { ctx -> getAppInfo(ctx) }
    }

    private fun getAppInfo(context: Context) {
        context.json(healthService.getAppInfo())
    }

}