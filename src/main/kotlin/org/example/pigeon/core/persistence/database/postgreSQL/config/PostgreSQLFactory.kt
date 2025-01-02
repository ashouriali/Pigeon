package org.example.pigeon.core.persistence.database.postgreSQL.config

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.example.pigeon.core.config.AppConfig
import org.example.pigeon.core.guice.Factory

import org.jetbrains.exposed.sql.Database

@Factory
class PostgreSQLFactory: AbstractModule() {

    @Provides
    @Singleton
    fun create(config: AppConfig): Database {
        val pgConfig = config.dataSource.postgreSQL
        val dataSource = HikariConfig().apply {
            this.poolName = pgConfig.hikari.poolName
            this.jdbcUrl = pgConfig.url
            this.username = pgConfig.username
            this.password = pgConfig.password
            this.driverClassName = pgConfig.hikari.driverClassName
        }.let { HikariDataSource(it) }
        return Database.connect(dataSource)
    }

}