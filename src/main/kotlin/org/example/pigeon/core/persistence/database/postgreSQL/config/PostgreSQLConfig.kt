package org.example.pigeon.core.persistence.database.postgreSQL.config

import com.google.inject.Inject
import com.google.inject.Singleton
import org.example.pigeon.core.persistence.config.BaseDataSourceConfig
import org.example.pigeon.core.persistence.config.BaseHikariConfig

@Singleton
class PostgreSQLConfig: BaseDataSourceConfig() {
    @Inject
    lateinit var hikari: BaseHikariConfig
}
