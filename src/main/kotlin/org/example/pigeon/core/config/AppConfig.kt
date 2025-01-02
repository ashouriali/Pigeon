package org.example.pigeon.core.config

import com.google.inject.Inject
import com.google.inject.Singleton
import org.example.pigeon.core.persistence.config.DataSource
import java.time.Duration

@Singleton
class AppConfig {
    var appName: String = ""
    var appMode: AppMode = AppMode.PROD
    var port: Int = 8080
    var host: String = "localhost"

    var loginTtl: Duration = Duration.ofDays(7)

    var applicationNumber: Int = 1

    @Inject
    lateinit var dataSource: DataSource
}