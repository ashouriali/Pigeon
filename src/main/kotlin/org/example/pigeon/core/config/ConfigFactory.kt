package org.example.pigeon.core.config


import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import org.example.pigeon.core.guice.Factory


@Factory
class ConfigFactory: AbstractModule() {
    private val config = ConfigFactory.load()

    @Provides
    fun provideConfig(): Config {
        return config
    }

}