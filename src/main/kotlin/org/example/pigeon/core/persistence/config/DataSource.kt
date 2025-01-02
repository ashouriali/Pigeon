package org.example.pigeon.core.persistence.config

import com.google.inject.Inject
import com.google.inject.Singleton
import org.example.pigeon.core.persistence.database.postgreSQL.config.PostgreSQLConfig



@Singleton
class DataSource {
    @Inject
    lateinit var postgreSQL: PostgreSQLConfig
}