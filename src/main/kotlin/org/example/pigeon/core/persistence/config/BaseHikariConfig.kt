package org.example.pigeon.core.persistence.config

open class BaseHikariConfig {

    lateinit var driverClassName: String
    var connectionTimeout: Long = 50000L
    var leakDetectionThreshold = 120000L
    var idleTimeout = 300000L
    var maxLifetime = 900000L
    var maximumPoolSize = 20L
    var minimumIdle = 10L
    var poolName = ""
}