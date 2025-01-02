package org.example.pigeon.core.service

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.example.pigeon.core.config.AppConfig

@Singleton
class HealthService @Inject constructor(private val config: AppConfig) {
    fun getAppInfo(): Any = object {
        val appName: String = config.appName
    }

}