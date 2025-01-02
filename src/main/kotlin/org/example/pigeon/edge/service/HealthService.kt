package org.example.pigeon.edge.service


import com.google.inject.Inject
import com.google.inject.Singleton
import org.example.pigeon.core.config.AppConfig

@Singleton
class HealthService @Inject constructor(private val config: AppConfig) {
    fun getAppInfo(): Any = object {
        val appName: String = config.appName
    }

}