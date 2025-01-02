package org.example.pigeon.core.config

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.example.pigeon.core.persistence.config.DataSource
import java.time.Duration

@Singleton
class AppConfig {
    var appName: String = ""

    var loginTtl: Duration = Duration.ofDays(7)

    var applicationNumber: Int = 1

    @Inject
    lateinit var dataSource: DataSource
}