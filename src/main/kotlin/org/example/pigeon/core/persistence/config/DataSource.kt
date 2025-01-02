package org.example.pigeon.core.persistence.config

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.example.pigeon.core.persistence.database.mssqlServer.config.PostgreSQLConfig


@Singleton
class DataSource {
    @Inject
    lateinit var postgreSQL: PostgreSQLConfig
}