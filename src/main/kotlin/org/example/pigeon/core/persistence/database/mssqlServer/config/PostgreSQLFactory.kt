package org.example.pigeon.core.persistence.database.mssqlServer.config

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import jakarta.inject.Singleton
import org.example.pigeon.core.config.AppConfig
import org.example.pigeon.core.guice.Factory

import org.jetbrains.exposed.sql.Database

@Factory
class PostgreSQLFactory: AbstractModule() {

    @Provides
    @Singleton
    fun create(config: AppConfig): Database {
//        if(TransformerApp.appMode == AppMode.TEST)


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